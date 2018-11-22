package io.github.takecare.cv

import dagger.Component
import io.github.takecare.cv.cover.CoverFragment
import io.github.takecare.cv.experience.ExperienceFragment

@Component(modules = [NetworkModule::class])
interface ApplicationInjector {

    fun inject(cvApplication: CvApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: CoverFragment)

    fun inject(fragment: ExperienceFragment)
}
