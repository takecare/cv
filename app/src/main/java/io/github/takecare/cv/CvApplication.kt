package io.github.takecare.cv

import android.app.Application
import android.util.Log
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CvApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val cvComponent: AppComponent = DaggerAppComponent.builder()
            .build()

        cvComponent.cvService().getCv().subscribeOn(Schedulers.io()).subscribeBy { Log.d("RUI", "$it") }
    }
}
