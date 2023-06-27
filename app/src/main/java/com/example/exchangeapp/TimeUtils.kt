package com.example.exchangeapp

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    fun convertTimeStampToTime(timeStamp: Long?): String {
        if (timeStamp == null) {
            return ""
        }
        val stamp = Timestamp(timeStamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val simpleDateRormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateRormat.timeZone = TimeZone.getDefault()
        return simpleDateRormat.format(date)
    }

    companion object {
        fun convertTimeStampToTime(timeStamp: Long?): String {
            if (timeStamp == null) {
                return ""
            }
            val stamp = Timestamp(timeStamp * 1000)
            val date = Date(stamp.time)
            val pattern = "HH:mm:ss"
            val simpleDateRormat = SimpleDateFormat(pattern, Locale.getDefault())
            simpleDateRormat.timeZone = TimeZone.getDefault()
            return simpleDateRormat.format(date)
        }
    }
}

