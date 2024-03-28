package com.example.myapp.data.network

import com.example.myapp.data.network.model.CurrencyListDto
import retrofit2.http.GET

interface ApiService {

    @GET("daily_json.js")
    suspend fun getCurrencyList(): CurrencyListDto
}