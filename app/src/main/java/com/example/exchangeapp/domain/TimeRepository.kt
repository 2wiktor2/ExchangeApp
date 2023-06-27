package com.example.exchangeapp.domain

import com.example.exchangeapp.data.database.AppStartTime

interface TimeRepository {

    fun saveTime(time: AppStartTime)
    fun getTime(): String

}