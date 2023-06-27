package com.example.exchangeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TimeDao {
    @Query("SELECT * FROM  AppStartTime")
    fun getTime(): Single<AppStartTime>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTime(appStartTime: AppStartTime)
}