package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: KataAdapter

    private val addKataResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Jika ada perubahan, reload data
            loadData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load data saat pertama kali membuka MainActivity
        loadData()

        val addButton: Button = findViewById(R.id.buttonAdd)
        addButton.setOnClickListener {
            // Menambahkan kata baru
            val intent = Intent(this, AddKataActivity::class.java)
            addKataResultLauncher.launch(intent)
        }
    }

    // Fungsi untuk memuat ulang data dari database
    private fun loadData() {
        val db = KataDatabase.getDatabase(this)

        lifecycleScope.launch {
            // Ambil data dari database
            val kataList = withContext(Dispatchers.IO) {
                db.kataDao().getAllKata()
            }

            // Update RecyclerView dengan data terbaru
            runOnUiThread {
                adapter = KataAdapter(kataList)
                recyclerView.adapter = adapter
            }
        }
    }
}
