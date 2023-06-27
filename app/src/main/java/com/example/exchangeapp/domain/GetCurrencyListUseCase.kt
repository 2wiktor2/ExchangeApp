package com.example.exchangeapp.domain

import androidx.lifecycle.LiveData

class GetCurrencyListUseCase(val repository: Repository) {

    fun getCurrencyList(): LiveData<List<CurrencyEntity>> {
        return repository.getCurrencyList()
    }
}