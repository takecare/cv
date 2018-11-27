package io.github.takecare.cv

object ComponentProviderHolder {

    var componentProvider: ComponentProvider = CvComponentProvider()
    fun createComponentProvider(): ComponentProvider = componentProvider
}
