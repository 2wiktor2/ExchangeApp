package com.example.exchangeapp.domain

import androidx.lifecycle.LiveData
import com.example.exchangeapp.data.database.AppStartTime

class GetTimeUseCase(val repository: TimeRepository) {

    fun getTime():LiveData<AppStartTime> {
        return repository.getTime()
    }
}