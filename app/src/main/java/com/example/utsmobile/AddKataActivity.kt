package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
        val kategoriInput: Spinner = findViewById(R.id.inputKategori)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        val db = KataDatabase.getDatabase(this)

        val kategoriOptions = arrayOf("GenZ", "Alpha", "Boomer")
        val kategoriAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kategoriOptions)
        kategoriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        kategoriInput.adapter = kategoriAdapter

        buttonSave.setOnClickListener {
            val kata = kataInput.text.toString()
            val arti = artiInput.text.toString()

            // Get the selected category from the Spinner
            val kategori = kategoriInput.selectedItem.toString()

            if (kata.isNotEmpty() && arti.isNotEmpty() && kategori.isNotEmpty()) {
                val newKata = Kata(kata = kata, arti = arti, kategori = kategori)

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        db.kataDao().insert(newKata)
                    }

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
