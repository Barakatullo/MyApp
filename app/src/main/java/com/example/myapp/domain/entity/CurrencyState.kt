package com.example.myapp.domain.entity

import java.sql.Timestamp

sealed class CurrencyState {

    class Success(
       val list: List<Currency>
        ) : CurrencyState()
    data object Loading : CurrencyState()

}