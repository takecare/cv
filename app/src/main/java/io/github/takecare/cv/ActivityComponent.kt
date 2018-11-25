package io.github.takecare.cv

import dagger.Component

@Component(modules = [MainActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun inject(activity: MainActivity)
}
