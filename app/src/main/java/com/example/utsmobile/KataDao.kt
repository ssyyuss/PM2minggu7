package com.example.utsmobile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KataDao {

    @Insert
    suspend fun insert(kata: Kata)

    @Query("SELECT * FROM kata_table")
    suspend fun getAllKata(): List<Kata>

}
