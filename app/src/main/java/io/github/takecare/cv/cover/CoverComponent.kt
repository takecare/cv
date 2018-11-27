package io.github.takecare.cv.cover

import dagger.Component
import io.github.takecare.cv.MainActivityComponent

@CoverScope
@Component(modules = [CoverModule::class], dependencies = [MainActivityComponent::class])
interface CoverComponent {

    fun inject(fragment: CoverFragment)
}
