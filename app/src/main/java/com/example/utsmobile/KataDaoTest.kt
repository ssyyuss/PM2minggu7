package com.example.utsmobile

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class KataDaoTest {

    private lateinit var database: KataDatabase
    private lateinit var kataDao: KataDao

    @Before
    fun setup() {
        // In-memory database supaya data hilang setelah test selesai
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            KataDatabase::class.java
        ).build()
        kataDao = database.kataDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetAllKata() = runBlocking {
        val kata1 = Kata(kata = "Bro", arti = "Teman dekat", kategori = "GenZ")
        val kata2 = Kata(kata = "Sok", arti = "Berlagak", kategori = "Boomer")

        kataDao.insert(kata1)
        kataDao.insert(kata2)

        val allKata = kataDao.getAllKata()

        assertEquals(2, allKata.size)
        assertTrue(allKata.any { it.kata == "Bro" && it.kategori == "GenZ" })
        assertTrue(allKata.any { it.kata == "Sok" && it.kategori == "Boomer" })
    }
}
