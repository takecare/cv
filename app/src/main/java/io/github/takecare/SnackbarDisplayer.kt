package io.github.takecare

import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View

class SnackbarDisplayer {

    private lateinit var snackbar: Snackbar
    private var isDisplayed = false

    fun display(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
        if (isDisplayed) {
            return
        }

        Log.d("RUI", "> display: $this")

        snackbar = Snackbar.make(view, message, duration)
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
                isDisplayed = true
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                isDisplayed = false
            }
        })

        snackbar.show()
    }
}
