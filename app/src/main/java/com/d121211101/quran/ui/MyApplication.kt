package com.d121211101.quran.ui

import android.app.Application
import com.d121211101.quran.data.AppContainer
import com.d121211101.quran.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}