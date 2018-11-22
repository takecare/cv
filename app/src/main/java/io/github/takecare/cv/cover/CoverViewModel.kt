package io.github.takecare.cv.cover

import android.support.annotation.DrawableRes

// displayed in the cover fragment:
// - list of cover items

data class CoverViewModel(
    val items: List<CoverItemViewModel>
)

sealed class CoverItemViewModel {
    data class Letter(val text: String) : CoverItemViewModel()
    data class Link(val text: String, val url: String, @DrawableRes val drawableRes: Int) : CoverItemViewModel()
    data class Knowledge(val text: String) : CoverItemViewModel()
}
