package com.example.exchangeapp.domain

data class CurrencyEntity(
    val name: String,
    val charCode: String,
    val nominal: Int,
    val value: Double,
    val previous: Double,
)
