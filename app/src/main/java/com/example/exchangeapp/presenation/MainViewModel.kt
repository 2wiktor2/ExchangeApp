package com.example.exchangeapp.presenation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exchangeapp.data.RepositoryIMPL
import com.example.exchangeapp.data.TimeRepositoryImpl
import com.example.exchangeapp.data.database.AppStartTime
import com.example.exchangeapp.domain.*


class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = RepositoryIMPL(application)
    private val timeRepository = TimeRepositoryImpl(application)

    private val getCurrencyListUseCase = GetCurrencyListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    private val getTimeUseCase = GetTimeUseCase(timeRepository)
    private val saveTimeUseCase = SaveTimeUseCase(timeRepository)


   val currencyList = getCurrencyListUseCase.getCurrencyList()



//    private val _currencyList = MutableLiveData<List<CurrencyEntity>>()
//    var currencyList: LiveData<List<CurrencyEntity>>
//        get() = _currencyList


    private val _hasConnectionLD = MutableLiveData<Boolean>()
    val hasConnectionLD: LiveData<Boolean>
        get() = _hasConnectionLD


    fun loadData() {
        setHasConnection()
        if (isNetworkAvailable(this.getApplication())) {
            Log.i("qwerty", "connection ============== true")
            Log.i("qwerty", "загрузка из сети")
            loadDataUseCase.loadData()
            getCurrencyListUseCase.getCurrencyList()
        } else {
            Log.i("qwerty", "connection ============== false")
            Log.i("qwerty", "загрузка из бд")
           getCurrencyListUseCase.getCurrencyList()
        }
    }

    private fun setHasConnection() {
        _hasConnectionLD.value = isNetworkAvailable(this.getApplication())
    }

    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || it.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    fun saveStartTime(time: Long) {
        Log.i("qwerty", "saveStartTime ============== time = $time")
        val appStartTime = AppStartTime(time = time)
        saveTimeUseCase.saveTime(appStartTime)
    }

    fun getStartTime(): String {
        return getTimeUseCase.getTime()
    }
}