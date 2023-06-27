package com.example.exchangeapp.data.network.model

import androidx.room.Entity
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

@Entity(tableName = "RawResponse")
data class RawResponseDto(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: JsonObject,
)
