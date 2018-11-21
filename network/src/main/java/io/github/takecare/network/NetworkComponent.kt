package io.github.takecare.network

import dagger.Component

@Component(modules = [CvServiceModule::class])
interface NetworkComponent {

    fun cvService(): CvService
}
