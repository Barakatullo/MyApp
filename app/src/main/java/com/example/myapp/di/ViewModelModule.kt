package com.example.myapp.di

import androidx.lifecycle.ViewModel
import com.example.myapp.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(mainViewModel: MainViewModel) : ViewModel
}