package io.github.takecare.cv.experience

import dagger.Component
import io.github.takecare.cv.ErrorDisplayModule

@Component(modules = [ExperienceModule::class, ErrorDisplayModule::class])
@ExperienceScope
interface ExperienceComponent {

    fun inject(experienceFragment: ExperienceFragment)
}
