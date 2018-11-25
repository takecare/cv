package io.github.takecare.cv

import android.app.Application

class CvApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .build()
    }
}
