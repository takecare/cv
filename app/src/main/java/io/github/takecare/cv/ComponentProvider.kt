package io.github.takecare.cv

import android.app.Activity
import android.content.Context
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

    lateinit var activityComponent: MainActivityComponent

    override fun activityComponent(context: Context): MainActivityComponent {
        activityComponent = DaggerMainActivityComponent.builder()
            .imageLoaderModule(ImageLoaderModule(context))
            .build()
        return activityComponent
    }

    override fun coverComponent(activity: Activity): CoverComponent {
        return DaggerCoverComponent.builder()
            .coverModule(CoverModule(activity))
            .mainActivityComponent(activityComponent)
            .build()
    }

    override fun experienceComponent(context: Context): ExperienceComponent {
        return DaggerExperienceComponent.builder()
            .imageLoaderModule(ImageLoaderModule(context))
            .mainActivityComponent(activityComponent)
            .build()
    }
}
