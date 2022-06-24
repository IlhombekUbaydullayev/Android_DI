package com.example.android_di.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DIApplication : Application(){

    @Inject
     lateinit var sampleClass: SampleClass

    override fun onCreate() {
        super.onCreate()
        sampleClass.doSomething()
    }
}