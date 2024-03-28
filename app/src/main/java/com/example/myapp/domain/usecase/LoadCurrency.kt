package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.CurrencyRepository
import javax.inject.Inject

class LoadCurrency@Inject constructor( private val repository: CurrencyRepository) {

    suspend operator fun invoke() = repository.loadCurrency()
}