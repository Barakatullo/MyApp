package com.example.myapp.domain.repository

import com.example.myapp.domain.entity.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface CurrencyRepository {

    fun getCurrencyPriceList() : Flow<List<Currency>>
    suspend fun loadCurrency()
    fun getTime(): Flow<String>
}