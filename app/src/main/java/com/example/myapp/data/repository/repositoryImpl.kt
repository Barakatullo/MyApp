package com.example.myapp.data.repository

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.data.mapper.Mapper
import com.example.myapp.data.network.ApiService
import com.example.myapp.domain.entity.Currency
import com.example.myapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

class RepositoryImpl@Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper
) : CurrencyRepository {

    private val refreshListFlow = MutableSharedFlow<Unit>(1)

    override  fun getCurrencyPriceList(): Flow<List<Currency>> = flow {
        refreshListFlow.emit(Unit)
        refreshListFlow.collect{ _ ->
            val list =  apiService.getCurrencyList().list.values.map {
                mapper.mapDtoToModel(it)
            }
            emit(list)
        }
    }.retry {
        delay(3000L)
        true
    }

     override suspend fun loadCurrency() {
            refreshListFlow.emit(Unit)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getTime(): Flow<String> = flow {
        emit(mapper.convertTimestampToTime(apiService.getCurrencyList().time))
    }
        .retry {
            delay(3000L)
            true
        }
}