package io.github.takecare.cv

import android.app.Application

class CvApplication : Application() {

    lateinit var injector: ApplicationInjector

    override fun onCreate() {
        super.onCreate()
        injector = DaggerApplicationInjector.builder().build()
    }
}
