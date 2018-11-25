package io.github.takecare

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Background

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Foreground
