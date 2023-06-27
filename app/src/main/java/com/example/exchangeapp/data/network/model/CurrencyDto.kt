package com.example.exchangeapp.data.network.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @PrimaryKey
    @SerializedName("Name")
    val name: String,

    @SerializedName("Value")
    val value: Double,
    @SerializedName("Previous")
    val previous: Double,
)
