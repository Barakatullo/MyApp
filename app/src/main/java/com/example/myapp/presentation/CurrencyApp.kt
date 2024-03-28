package com.example.myapp.presentation

import android.app.Application
import com.example.myapp.di.DaggerApplicationComponent

class CurrencyApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}