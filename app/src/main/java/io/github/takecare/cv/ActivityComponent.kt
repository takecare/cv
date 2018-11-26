package io.github.takecare.cv

import dagger.Component
import io.github.takecare.ActivityScope

@Component(modules = [MainActivityModule::class], dependencies = [ErrorDisplayComponent::class])
@ActivityScope
interface ActivityComponent {

    fun inject(activity: MainActivity)
}
