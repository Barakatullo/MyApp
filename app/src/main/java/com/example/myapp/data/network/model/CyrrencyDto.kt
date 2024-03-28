package com.example.myapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("Name") val name : String,
    @SerializedName("Value") val price : String,
    @SerializedName("CharCode") val charCode : String,
)
