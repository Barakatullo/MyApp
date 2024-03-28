package com.example.myapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.domain.entity.Currency

class CurrenctAdapter(val context: Context) : ListAdapter<Currency, CurrencyViewHolder>(CurrencyDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.price_item,
                parent,
                false
            )

        return CurrencyViewHolder(view)
    }


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = getItem(position)
        val symbolsTemplate = context.resources.getString(R.string.charCode)
        holder.tvSymbol.text = String.format(symbolsTemplate, currency.charCode)
        holder.tvName.text = currency.name
        holder.tvPrice.text = currency.price
    }
}

class CurrencyViewHolder(
    val view: View
    ) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
    val tvSymbol = view.findViewById<TextView>(R.id.tvSymbols)
    }
