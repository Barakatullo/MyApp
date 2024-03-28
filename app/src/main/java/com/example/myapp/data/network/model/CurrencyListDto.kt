package com.example.myapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrencyListDto(
    @SerializedName("Valute") val list : Map<String,CurrencyDto>,
    @SerializedName("Timestamp") val time : String
)
