package com.example.myapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapp.domain.entity.Currency

object CurrencyDiffCallback : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.charCode == newItem.charCode
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}
