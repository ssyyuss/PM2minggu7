package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: KataListAdapter

    private val addKataResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            loadData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = KataListAdapter()
        recyclerView.adapter = adapter

        loadData()

        val addButton: Button = findViewById(R.id.buttonAdd)
        addButton.setOnClickListener {
            val intent = Intent(this, AddKataActivity::class.java)
            addKataResultLauncher.launch(intent)
        }
    }


    private fun loadData() {
        val db = KataDatabase.getDatabase(this)

        lifecycleScope.launch {
            val kataList = withContext(Dispatchers.IO) {
                db.kataDao().getAllKata()
            }

            val orderedCategories = listOf("GenZ", "Alpha", "Boomer")
            val combinedList = mutableListOf<KataItem>()

            for (kategori in orderedCategories) {
                val items = kataList.filter { it.kategori == kategori }
                if (items.isNotEmpty()) {
                    combinedList.add(KataItem.Header("Kategori: $kategori"))
                    combinedList.addAll(items.map { KataItem.KataData(it) })
                }
            }

            adapter.submitList(combinedList)
        }
    }

}
