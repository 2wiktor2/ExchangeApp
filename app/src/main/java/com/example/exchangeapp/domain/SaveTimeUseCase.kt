package com.example.exchangeapp.domain

import com.example.exchangeapp.data.database.AppStartTime

class SaveTimeUseCase(val repository: TimeRepository) {

    fun saveTime(time: AppStartTime) {
        repository.saveTime(time)
    }
}