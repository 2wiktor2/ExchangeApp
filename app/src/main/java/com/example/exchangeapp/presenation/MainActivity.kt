package com.example.exchangeapp.presenation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exchangeapp.App
import com.example.exchangeapp.R
import com.example.exchangeapp.databinding.ActivityMainBinding
import com.example.exchangeapp.domain.CurrencyEntity
import com.example.exchangeapp.presenation.adapters.CurrencyAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = CurrencyAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = CurrencyAdapter(this)
        binding.recyclerViewCurrencies.adapter = adapter


        val tempCurrencyEntity = CurrencyEntity("111","222",333, 444.0, 555.0)
        val tempList = listOf(tempCurrencyEntity)
        adapter.currencyList = tempList


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setListeners()

        viewModel.currencyList.observe(this, Observer {
            adapter.currencyList = it
        })


        viewModel.hasConnectionLD.observe(this) {
            Log.i("qwerty", "connection ============== $it")
            showWarning(this, it)
        }

        viewModel.saveStartTime(App.currentDate.time)
        showToast(App.currentDate.time.toString())
    }

    private fun setListeners() {
        binding.buttonLoad.setOnClickListener {
            observeViewModel()
            viewModel.loadData()
            viewModel.getStartTime()
            viewModel.saveStartTime(App.currentDate.time)
        }
    }

    private fun observeViewModel() {
        viewModel.currencyList.observe(this, Observer {
            adapter.currencyList = it
        })
    }

    private fun showWarning(context: Context, isConnected: Boolean) {
        with(binding.textViewDate) {
            if (isConnected) {
                text = context.getString(R.string.load_from_network_warning)
                setTextColor(
                    ContextCompat.getColor(
                        context, android.R.color.holo_green_light
                    )
                )
            } else {
                text = context.getString(R.string.load_from_database_warning)
                setTextColor(
                    ContextCompat.getColor(
                        context, android.R.color.holo_red_dark
                    )
                )
                showToast(context.getString(R.string.network_warning))
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
