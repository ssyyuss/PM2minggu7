package com.example.utsmobile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kata_table")
data class Kata(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val kata: String,
    val arti: String,
    val kategori: String
)
