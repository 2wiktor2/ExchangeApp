package com.example.exchangeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "AppStartTime")
data class AppStartTime(
    @PrimaryKey
    val id: Int = 1,
    val time: Long,
)
