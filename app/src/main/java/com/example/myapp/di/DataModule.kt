package com.example.myapp.di

import com.example.myapp.data.network.ApiFactory
import com.example.myapp.data.network.ApiService
import com.example.myapp.data.repository.RepositoryImpl
import com.example.myapp.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: RepositoryImpl): CurrencyRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}