package com.example.exchangeapp.domain

import androidx.lifecycle.LiveData
import com.example.exchangeapp.data.database.AppStartTime

interface TimeRepository {

    fun saveTime(time: AppStartTime)
    fun getTime(): LiveData<AppStartTime>

}