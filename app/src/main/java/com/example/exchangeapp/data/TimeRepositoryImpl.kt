package com.example.exchangeapp.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.exchangeapp.TimeUtils
import com.example.exchangeapp.data.database.AppDataBase
import com.example.exchangeapp.data.database.AppStartTime
import com.example.exchangeapp.domain.TimeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeRepositoryImpl(application: Application) : TimeRepository {

    private val timeDao = AppDataBase.getInstance(application).timeDao()


    override fun saveTime(time: AppStartTime) {
        val disposable = timeDao.insertTime(time).subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getTime(): LiveData<AppStartTime> {

        return timeDao.getTime()


    }
}