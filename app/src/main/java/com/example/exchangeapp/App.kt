package com.example.exchangeapp

import android.app.Application
import java.util.Calendar

class App : Application() {


    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        val currentDate = Calendar.getInstance().time
        val time = TimeUtils.convertTimeStampToTime(currentDate.time)
    }

}