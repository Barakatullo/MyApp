package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyPriceList@Inject constructor(private val repository: CurrencyRepository) {

     operator fun invoke() = repository.getCurrencyPriceList()
}