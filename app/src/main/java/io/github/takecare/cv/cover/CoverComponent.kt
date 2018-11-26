package io.github.takecare.cv.cover

import dagger.Component

@Component(modules = [CoverModule::class])
@CoverScope
interface CoverComponent {

    fun inject(fragment: CoverFragment)
}
