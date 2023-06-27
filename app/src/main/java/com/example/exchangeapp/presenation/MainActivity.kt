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
import com.example.exchangeapp.TimeUtils
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

        binding.recyclerViewCurrencies.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setListeners()

        viewModel.currencyList.observe(this, Observer {
            adapter.currencyList = it
        })

        viewModel.hasConnectionLD.observe(this) {
            showWarning(this, it)
        }

        viewModel.saveStartTime(App.currentDate)
        viewModel.startTime.observe(this) {
            val t = TimeUtils.convertTimeStampToTime(it.time)
            binding.textViewTimeInfo.text = getString(R.string.start_time) + t
        }

    }

    private fun setListeners() {
        binding.buttonLoad.setOnClickListener {
            viewModel.loadData()
            viewModel.saveStartTime(App.currentDate)
        }
    }


    private fun showWarning(context: Context, isConnected: Boolean) {
        with(binding.textViewWarning) {
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
