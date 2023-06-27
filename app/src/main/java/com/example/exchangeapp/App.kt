package com.example.exchangeapp

import android.app.Application
import java.util.Calendar

class App : Application() {
    companion object {
        val currentDate = Calendar.getInstance().timeInMillis
        val time = TimeUtils.convertTimeStampToTime(currentDate)
    }

}