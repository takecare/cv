package io.github.takecare.cv.cover

import dagger.Component
import io.github.takecare.cv.ErrorDisplayComponent

@Component(modules = [CoverModule::class], dependencies = [ErrorDisplayComponent::class])
@CoverScope
interface CoverComponent {

    fun inject(fragment: CoverFragment)
}
