package io.github.takecare.cv.experience

import dagger.Component

@Component(modules = [ExperienceModule::class])
@ExperienceScope
interface ExperienceComponent {

    fun inject(experienceFragment: ExperienceFragment)
}
