package io.github.takecare.cv

import android.content.Context
import io.github.takecare.cv.cover.CoverComponent
import io.github.takecare.cv.experience.ExperienceComponent

class TestComponentProvider(
    private val defaultProvider: ComponentProvider = CvComponentProvider(),
    private val activityComponent: MainActivityComponent? = null,
    private val coverComponent: CoverComponent? = null,
    private val experienceComponent: ExperienceComponent? = null
) : ComponentProvider {

    override fun activityComponent(context: Context): MainActivityComponent =
        activityComponent ?: defaultProvider.activityComponent(context)

    override fun coverComponent(): CoverComponent =
        coverComponent ?: defaultProvider.coverComponent()

    override fun experienceComponent(): ExperienceComponent =
        experienceComponent ?: defaultProvider.experienceComponent()
}
