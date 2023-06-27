package com.example.exchangeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class CurrencyDbModel(
    @PrimaryKey
    val name: String,
    val charCode: String,
    val nominal: Int,
    val value: Double,
    val previous: Double,
)

