package com.example.profile.utils

import android.view.View

object Const {
    const val NETWORK_ERROR_OCCURRED = "network_error_occurred"
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

