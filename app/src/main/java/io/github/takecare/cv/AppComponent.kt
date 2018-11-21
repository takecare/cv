package io.github.takecare.cv

import dagger.Component
import io.github.takecare.network.CvService

@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun cvService(): CvService
}
