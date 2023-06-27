package com.example.exchangeapp.domain

class GetTimeUseCase(val repository: TimeRepository) {

    fun getTime(): String {
        return repository.getTime()
    }
}