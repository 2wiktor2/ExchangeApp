package com.example.exchangeapp.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.exchangeapp.data.database.AppDataBase
import com.example.exchangeapp.data.mapper.CurrencyMapper
import com.example.exchangeapp.data.network.ApiFactory
import com.example.exchangeapp.domain.Repository
import com.example.exchangeapp.domain.CurrencyEntity
import io.reactivex.schedulers.Schedulers

class RepositoryIMPL(application: Application) : Repository {

    private val currencyDao = AppDataBase.getInstance(application).currencyDao()
    private val mapper = CurrencyMapper()

    override fun getCurrencyList(): LiveData<List<CurrencyEntity>> {
        return Transformations.map(currencyDao.getCurrencies()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun loadData() {
        val disposable = ApiFactory.apiService.getCurrency()
            .subscribeOn(Schedulers.io())
            .map { mapper.mapResponseToCurrencyList(it) }
            .subscribe({
                Log.i("qwerty", it.toString())
                currencyDao.insert(it.map { mapper.mapDtoToDbModel(it) })
            }, {
                Log.i("qwerty", "Throwable = ${it.message}")
            })

    }

}