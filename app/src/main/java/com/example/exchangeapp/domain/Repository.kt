package com.example.exchangeapp.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun getCurrencyList(): LiveData<List<CurrencyEntity>>

    fun loadData()

}