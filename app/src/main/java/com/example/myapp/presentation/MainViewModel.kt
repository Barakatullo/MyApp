package com.example.myapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapp.domain.entity.CurrencyState
import com.example.myapp.domain.usecase.GetCurrencyPriceList
import com.example.myapp.domain.usecase.GetTime
import com.example.myapp.domain.usecase.LoadCurrency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val loadCurrency: LoadCurrency,
    private val getCurrencyPriceList: GetCurrencyPriceList,
    private val getTime: GetTime,
) : ViewModel() {
    private val loadingFlow = MutableSharedFlow<CurrencyState>()
    private val scope = CoroutineScope(Dispatchers.IO)

    val timeState = getTime()
    val state: Flow<CurrencyState> = getCurrencyPriceList()
        .map {
            CurrencyState.Success(it) as CurrencyState
        }.mergeWith(loadingFlow)

    private fun refreshList() {
        scope.launch {
            while (true) {
                loadingFlow.emit(CurrencyState.Loading)
                delay(1000)
                loadCurrency()
                delay(30000)
            }
        }
    }

    init {
        scope.launch {
            refreshList()
        }
    }

    private fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }
}