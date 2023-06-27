package com.example.exchangeapp.presenation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeapp.PriceUtils
import com.example.exchangeapp.R
import com.example.exchangeapp.domain.CurrencyEntity

class CurrencyAdapter(val context: Context) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var currencyList: List<CurrencyEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val myCurrency = currencyList[position]

        val diffrerencePrice = myCurrency.value - myCurrency.previous

        with(holder) {
            textViewName.text = myCurrency.name
            textViewNominal.text = myCurrency.nominal.toString()
            textViewSymbols.text = myCurrency.charCode
            textViewPrice.text = myCurrency.value.toString()
            textViewPriceDifference.text = PriceUtils.roundPrice(diffrerencePrice)


            when {
                diffrerencePrice >= 0 -> textViewPriceDifference.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        android.R.color.holo_green_light
                    )
                )
                diffrerencePrice < 0 -> textViewPriceDifference.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        android.R.color.holo_red_dark
                    )
                )

                else -> textViewPriceDifference.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        android.R.color.black
                    )
                )
            }

        }
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNominal = itemView.findViewById<TextView>(R.id.textViewNominal)
        val textViewSymbols = itemView.findViewById<TextView>(R.id.textViewSymbols)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textViewPrice)
        val textViewPriceDifference = itemView.findViewById<TextView>(R.id.textViewPriceDifference)
    }
}