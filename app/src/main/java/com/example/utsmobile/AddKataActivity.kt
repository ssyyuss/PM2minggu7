package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddKataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kata)

        val kataInput: EditText = findViewById(R.id.inputKata)
        val artiInput: EditText = findViewById(R.id.inputArti)
        val kategoriInput: EditText = findViewById(R.id.inputKategori)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        val db = KataDatabase.getDatabase(this)

        buttonSave.setOnClickListener {
            val kata = kataInput.text.toString()
            val arti = artiInput.text.toString()
            val kategori = kategoriInput.text.toString()

            if (kata.isNotEmpty() && arti.isNotEmpty() && kategori.isNotEmpty()) {
                val newKata = Kata(kata = kata, arti = arti, kategori = kategori)

                lifecycleScope.launch {
                    // Simpan kata baru ke database
                    withContext(Dispatchers.IO) {
                        db.kataDao().insert(newKata)
                    }

                    // Kirim kembali ke MainActivity untuk merefresh data
                    val intent = Intent()
                    setResult(RESULT_OK, intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
