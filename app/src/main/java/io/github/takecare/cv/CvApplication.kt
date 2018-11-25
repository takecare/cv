package io.github.takecare.cv

import android.app.Application
import io.github.takecare.ApplicationComponent
import io.github.takecare.DaggerApplicationComponent

class CvApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .build()
    }
}
