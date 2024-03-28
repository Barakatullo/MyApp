package com.example.myapp.di

import android.app.Application
import com.example.myapp.presentation.CurrencyApp
import com.example.myapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: CurrencyApp)
    fun inject(activity: MainActivity)
    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}