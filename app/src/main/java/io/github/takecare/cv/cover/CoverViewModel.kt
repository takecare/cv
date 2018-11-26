package io.github.takecare.cv.cover

import android.support.annotation.DrawableRes

data class CoverViewModel(
    val items: List<CoverItemViewModel>
)

sealed class CoverItemViewModel {
    data class Letter(val text: String) : CoverItemViewModel()
    data class Link(val text: String, val url: String, @DrawableRes val drawableRes: Int) : CoverItemViewModel()
    data class Knowledge(val title: String, val text: String) : CoverItemViewModel()
}
