package com.devtech.mditest.utils

import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


fun TextView.changeDrawableColor(color: Int) {
    for (drawable in this.compoundDrawables) {
        if (drawable != null) {
            drawable.colorFilter =
                PorterDuffColorFilter(ContextCompat.getColor(this.context, color), PorterDuff.Mode.SRC_IN)
        }
    }
}

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}