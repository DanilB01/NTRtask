package com.example.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app.database.dao.*
import com.example.app.database.dto.*

@Database(entities = [EntityDB::class, DataDB::class, StatusDB::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun entitiesDao(): EntityDao
    abstract fun dataDao(): DataDao
    abstract fun statusesDao(): StatusDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}