package com.example.androiddi.utils

import android.util.Log
import javax.inject.Inject

class SampleClass @Inject constructor(){

    fun doSomething() {
        Log.d("SampleClass","Do some work...")
    }
}