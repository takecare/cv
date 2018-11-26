package io.github.takecare.cv.cover

import dagger.Component
import io.github.takecare.cv.ErrorDisplayModule

@Component(modules = [CoverModule::class, ErrorDisplayModule::class])
@CoverScope
interface CoverComponent {

    fun inject(fragment: CoverFragment)
}
