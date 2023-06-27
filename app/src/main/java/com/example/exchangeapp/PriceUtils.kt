package com.example.exchangeapp

import java.math.RoundingMode
import java.text.DecimalFormat

class PriceUtils {

    companion object {

        fun roundPrice(price: Double): String {
            val decimalFormat = DecimalFormat("#.###")
            decimalFormat.roundingMode = RoundingMode.DOWN
            return decimalFormat.format(price)
        }
    }
}