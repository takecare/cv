package io.github.takecare.cv

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.takecare.network.CvSourceServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = CvSourceServiceBuilder().build()
        disposable = service.getLatest(
            "takecare",
            "861509cde97d41a23a1cfa9bb9664b41"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { Log.d("RUI", "$it") },
                onError = { Log.e("RUI", "$it") }
            )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
