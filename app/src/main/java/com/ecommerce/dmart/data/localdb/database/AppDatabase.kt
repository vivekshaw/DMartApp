package com.ecommerce.dmart.data.localdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ecommerce.dmart.data.localdb.dao.CharacterDao
import com.ecommerce.dmart.data.localdb.entities.ProductEntity

@Database(entities = arrayOf(ProductEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "dmart")
                .fallbackToDestructiveMigration()
                .build()
    }

}