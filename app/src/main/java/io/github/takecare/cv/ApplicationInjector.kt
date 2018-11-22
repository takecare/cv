package io.github.takecare.cv

import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationInjector {

    fun inject(cvApplication: CvApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: CoverFragment)

    fun inject(fragment: ExperienceFragment)
}
