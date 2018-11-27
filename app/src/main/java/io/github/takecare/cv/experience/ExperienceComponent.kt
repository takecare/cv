package io.github.takecare.cv.experience

import dagger.Component
import io.github.takecare.cv.MainActivityComponent

@Component(modules = [ExperienceModule::class], dependencies = [MainActivityComponent::class])
@ExperienceScope
interface ExperienceComponent {

    fun inject(experienceFragment: ExperienceFragment)
}
