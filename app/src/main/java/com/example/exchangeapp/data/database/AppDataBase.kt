package com.example.exchangeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyDbModel::class, AppStartTime::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var db: AppDataBase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()


        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        DB_NAME
                    ).allowMainThreadQueries()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun currencyDao(): CurrencyDao
    abstract fun timeDao(): TimeDao

}