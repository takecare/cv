package io.github.takecare.cv

import android.app.Activity
import android.content.Context
import android.util.Log
import io.github.takecare.cv.cover.CoverComponent
import io.github.takecare.cv.cover.CoverModule
import io.github.takecare.cv.cover.DaggerCoverComponent
import io.github.takecare.cv.experience.DaggerExperienceComponent
import io.github.takecare.cv.experience.ExperienceComponent
import io.github.takecare.network.ImageLoaderModule

interface ComponentProvider {

    fun activityComponent(context: Context): MainActivityComponent

    fun coverComponent(activity: Activity): CoverComponent

    fun experienceComponent(context: Context): ExperienceComponent
}

class CvComponentProvider : ComponentProvider {

    private lateinit var _activityComponent: MainActivityComponent

    override fun activityComponent(context: Context): MainActivityComponent {
        if (!this::_activityComponent.isInitialized) {
            Log.d("RUI", "instantiating activity component")
            _activityComponent = DaggerMainActivityComponent.builder()
                .imageLoaderModule(ImageLoaderModule(context))
                .build()
        }
        return _activityComponent
    }

    override fun coverComponent(activity: Activity): CoverComponent {
        return DaggerCoverComponent.builder()
            .coverModule(CoverModule(activity))
            .mainActivityComponent(_activityComponent)
            .build()
    }

    override fun experienceComponent(context: Context): ExperienceComponent {
        return DaggerExperienceComponent.builder()
            .imageLoaderModule(ImageLoaderModule(context))
            .mainActivityComponent(_activityComponent)
            .build()
    }
}
