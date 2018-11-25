package io.github.takecare.cv.experience

import dagger.Component

@ExperienceScope
@Component(modules = [ExperienceModule::class])
interface ExperienceComponent {

    fun inject(experienceFragment: ExperienceFragment)
}
