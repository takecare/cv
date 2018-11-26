package io.github.takecare.cv.cover

import android.app.Activity
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import io.github.takecare.cv.R

interface LinkOpener {

    fun openLink(url: String)
}

class CustomTabsLinkOpener(
    private val intentBuilder: CustomTabsIntent.Builder,
    private val activity: Activity
) : LinkOpener {

    override fun openLink(url: String) {
        val intent = intentBuilder
            .setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary))
            .addDefaultShareMenuItem()
            .build()
        intent.launchUrl(activity, Uri.parse(url))
    }
}
