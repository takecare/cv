package io.github.takecare.cv

import android.app.Activity
import android.app.Application
import io.github.takecare.ApplicationComponent
import io.github.takecare.DaggerApplicationComponent

class CvApplication : Application() {

    companion object {
        fun get(activity: Activity): CvApplication {
            return activity.application as CvApplication
        }
    }

    private lateinit var component: ApplicationComponent

    lateinit var componentProvider: ComponentProvider

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .build()

        componentProvider = CvComponentProvider()
    }
}
