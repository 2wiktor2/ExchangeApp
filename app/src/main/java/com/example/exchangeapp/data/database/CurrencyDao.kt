package com.example.exchangeapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM  Currency ORDER BY name")
    fun getCurrencies(): LiveData<List<CurrencyDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<CurrencyDbModel>)

}