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

    fun activityComponent(context: Context): ActivityComponent

    fun coverComponent(activity: Activity): CoverComponent

    fun experienceComponent(): ExperienceComponent
}

class CvComponentProvider : ComponentProvider {

    override fun activityComponent(context: Context): ActivityComponent {
        return DaggerActivityComponent.builder()
            .imageLoaderModule(ImageLoaderModule(context))
            .build()
    }

    override fun coverComponent(activity: Activity): CoverComponent {
        return DaggerCoverComponent.builder()
            .coverModule(CoverModule(activity))
            .build()
    }

    override fun experienceComponent(): ExperienceComponent {
        return DaggerExperienceComponent.builder()
            .build()
    }
}
