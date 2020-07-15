package com.example.roomdatabasemanagermoney.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(ManagerMoney::class)], version = 1, exportSchema = false)
abstract class DatabaseDB : RoomDatabase() {

    abstract val managerMoneyDAO: ManagerMoneyDAO

    companion object {
        @Volatile
        private var INSTANCE: DatabaseDB? = null
        fun getInstance(context: Context): DatabaseDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseDB::class.java,
                        "managermoney_data_database").build()
                }
                return instance
            }
        }

    }
}