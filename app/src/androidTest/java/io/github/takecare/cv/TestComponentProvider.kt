package io.github.takecare.cv

import android.app.Activity
import android.content.Context
import io.github.takecare.cv.cover.CoverComponent
import io.github.takecare.cv.experience.ExperienceComponent

class TestComponentProvider(
    var defaultProvider: ComponentProvider = CvComponentProvider(),
    var activityComponent: MainActivityComponent? = null,
    var coverComponent: CoverComponent? = null,
    var experienceComponent: ExperienceComponent? = null
) : ComponentProvider {

    override fun coverComponent(activity: Activity) =
        coverComponent ?: defaultProvider.coverComponent(activity)

    override fun experienceComponent(context: Context) =
        experienceComponent ?: defaultProvider.experienceComponent(context)

    override fun activityComponent(context: Context): MainActivityComponent =
        activityComponent ?: defaultProvider.activityComponent(context)

}
