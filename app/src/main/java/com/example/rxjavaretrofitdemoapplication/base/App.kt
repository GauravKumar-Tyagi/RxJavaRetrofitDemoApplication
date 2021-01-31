package com.example.rxjavaretrofitdemoapplication.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)


    }
}