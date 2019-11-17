package com.project.kotlinnews.Utilities

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by it on 11/17/2019.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}