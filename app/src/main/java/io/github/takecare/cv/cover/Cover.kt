package io.github.takecare.cv.cover

import android.support.annotation.DrawableRes

sealed class Cover {
    data class Letter(val text: String) : Cover()
    data class Link(val text: String, val url: String, @DrawableRes val drawableRes: Int) : Cover()
    data class Knowledge(val text: String) : Cover()
}
