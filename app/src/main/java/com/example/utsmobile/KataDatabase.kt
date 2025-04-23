package com.example.utsmobile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kata::class], version = 1, exportSchema = false)
abstract class KataDatabase : RoomDatabase() {

    abstract fun kataDao(): KataDao

    companion object {
        @Volatile
        private var INSTANCE: KataDatabase? = null

        fun getDatabase(context: Context): KataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KataDatabase::class.java,
                    "kata_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
