package io.github.takecare.cv

import dagger.Component
import io.github.takecare.network.CvService
import io.github.takecare.network.CvServiceModule

//@Component(dependencies = [NetworkComponent::class])
@Component(modules = [CvServiceModule::class])
interface AppComponent {

    fun cvService(): CvService
}
