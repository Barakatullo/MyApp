package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetTime@Inject constructor(private val repository: CurrencyRepository) {

       operator fun invoke() = repository.getTime()
}