package com.example.exchangeapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TimeDao {
    @Query("SELECT * FROM  AppStartTime where id = 1")
    fun getTime(): LiveData<AppStartTime>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTime(appStartTime: AppStartTime): Completable
}