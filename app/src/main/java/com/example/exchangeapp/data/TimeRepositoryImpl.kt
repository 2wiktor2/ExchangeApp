package com.example.exchangeapp.data

import android.app.Application
import com.example.exchangeapp.TimeUtils
import com.example.exchangeapp.data.database.AppDataBase
import com.example.exchangeapp.data.database.AppStartTime
import com.example.exchangeapp.domain.TimeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeRepositoryImpl(application: Application) : TimeRepository {

    private val timeDao = AppDataBase.getInstance(application).timeDao()
    private val timeUtils = TimeUtils()


    override fun saveTime(time: AppStartTime) {
        timeDao.insertTime(time)
    }

    override fun getTime(): String {

        var timeResult = ""

        val disposable = timeDao.getTime()
            .subscribeOn(Schedulers.io())
            .map { timeUtils.convertTimeStampToTime(it.time) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                timeResult = it
            }, {

            })
        return timeResult
    }
}