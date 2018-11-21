package io.github.takecare.cv

import android.app.Application

class CvApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        val cvComponent: AppComponent = DaggerAppComponent.builder()
//            .networkComponent(DaggerNet)
//            .build()
//
//        cvComponent.cvService().getCv().subscribeOn(Schedulers.io()).subscribeBy { Log.d("RUI", "$it") }
    }
}
