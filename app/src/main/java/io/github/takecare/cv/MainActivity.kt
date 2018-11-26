package io.github.takecare.cv

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.takecare.cv.cover.CoverFragment
import io.github.takecare.cv.experience.ExperienceFragment
import io.github.takecare.network.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

private const val PERCENTAGE_AT_WHICH_AVATAR_IS_HIDDEN = 20

class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener, MainView {

    private var isAvatarDisplayed = true
    private var maxScrollSize = 0

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        viewpager.adapter = TabsAdapter(supportFragmentManager)
        tablayout.setupWithViewPager(viewpager)

        appbar_layout.addOnOffsetChangedListener(this)
        maxScrollSize = appbar_layout.totalScrollRange
    }

    override fun onStart() {
        super.onStart()
        presenter.startPresenting(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun show(viewModel: MainViewModel) {
        name.text = viewModel.name
        imageLoader.loadAsCircle(viewModel.photoUrl, profile_image)
    }

    override fun showError(throwable: Throwable) {
        // TODO: showError not implemented
        Log.e("RUI", "error: $throwable")
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (maxScrollSize == 0) {
            maxScrollSize = appBarLayout.totalScrollRange
        }

        val percentageOfLayoutHidden = Math.abs(verticalOffset) * 100 / maxScrollSize
        if (percentageOfLayoutHidden >= PERCENTAGE_AT_WHICH_AVATAR_IS_HIDDEN && isAvatarDisplayed) {
            isAvatarDisplayed = false
            profile_image.animate()
                .scaleY(0f).scaleX(0f)
                .setDuration(200)
                .start()
        }

        if (percentageOfLayoutHidden <= PERCENTAGE_AT_WHICH_AVATAR_IS_HIDDEN && !isAvatarDisplayed) {
            isAvatarDisplayed = true
            profile_image.animate()
                .scaleY(1f).scaleX(1f)
                .start()
        }
    }
}

fun MainActivity.injectDependencies() {
    CvApplication.get(this)
        .componentProvider
        .activityComponent(this)
        .inject(this)
}

private const val NUM_FRAGMENTS = 2

private class TabsAdapter internal constructor(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return NUM_FRAGMENTS
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            CoverFragment.newInstance()
        } else {
            ExperienceFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + position.toString()
    }
}
