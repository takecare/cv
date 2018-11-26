package io.github.takecare.cv

import android.content.Context
import io.github.takecare.cv.cover.CoverComponent
import io.github.takecare.cv.cover.DaggerCoverComponent
import io.github.takecare.cv.experience.DaggerExperienceComponent
import io.github.takecare.cv.experience.ExperienceComponent
import io.github.takecare.network.ImageLoaderModule

interface ComponentProvider {

    fun errorDisplayComponent(): ErrorDisplayComponent

    fun activityComponent(context: Context): ActivityComponent

    fun coverComponent(): CoverComponent

    fun experienceComponent(): ExperienceComponent
}

class CvComponentProvider : ComponentProvider {

    private lateinit var _errorDisplayComponent: ErrorDisplayComponent

    @Synchronized override fun errorDisplayComponent(): ErrorDisplayComponent {
        if (!this::_errorDisplayComponent.isInitialized) {
            _errorDisplayComponent = DaggerErrorDisplayComponent.create()
        }
        return _errorDisplayComponent
    }

    override fun activityComponent(context: Context): ActivityComponent {
        return DaggerActivityComponent.builder()
            .imageLoaderModule(ImageLoaderModule(context))
            .errorDisplayComponent(errorDisplayComponent())
            .build()
    }

    override fun coverComponent(): CoverComponent {
        return DaggerCoverComponent.builder()
            .errorDisplayComponent(errorDisplayComponent())
            .build()
    }

    override fun experienceComponent(): ExperienceComponent {
        return DaggerExperienceComponent.builder()
            .errorDisplayComponent(errorDisplayComponent())
            .build()
    }
}
