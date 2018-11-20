package io.github.takecare.cv

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.takecare.network.CvService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var disposable: Disposable
    private var isAvatarDisplayed = true
    private var maxScrollSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = CvService()
        disposable = service.getCv()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { Log.d("RUI", "$it") },
                        onError = { Log.e("RUI", "$it") }
                )

        appbar_layout.addOnOffsetChangedListener(this)
        maxScrollSize = appbar_layout.totalScrollRange
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (maxScrollSize == 0) {
            maxScrollSize = appBarLayout.totalScrollRange
        }

        val percentage = Math.abs(i) * 100 / maxScrollSize

        if (percentage >= 20 && isAvatarDisplayed) {
            isAvatarDisplayed = false

            profile_image.animate()
                    .scaleY(0f).scaleX(0f)
                    .setDuration(200)
                    .start()
        }

        if (percentage <= 20 && !isAvatarDisplayed) {
            isAvatarDisplayed = true

            profile_image.animate()
                    .scaleY(1f).scaleX(1f)
                    .start()
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
