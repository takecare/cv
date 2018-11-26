package io.github.takecare.cv.experience

import dagger.Component
import io.github.takecare.cv.ErrorDisplayComponent

@Component(modules = [ExperienceModule::class], dependencies = [ErrorDisplayComponent::class])
@ExperienceScope
interface ExperienceComponent {

    fun inject(experienceFragment: ExperienceFragment)
}
