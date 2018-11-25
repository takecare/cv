package io.github.takecare.cv

import dagger.Component
import io.github.takecare.cv.experience.ExperienceFragment

@Component
@ApplicationScope
interface ApplicationComponent {

    fun inject(fragment: ExperienceFragment) // TODO move to CoverComponent
}
