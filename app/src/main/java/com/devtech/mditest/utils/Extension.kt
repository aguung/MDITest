package com.devtech.mditest.utils

import android.graphics.PorterDuff

import androidx.core.content.ContextCompat

import android.graphics.PorterDuffColorFilter

import android.graphics.drawable.Drawable

import android.widget.TextView


fun TextView.changeDrawableColor(color: Int) {
    for (drawable in this.compoundDrawables) {
        if (drawable != null) {
            drawable.colorFilter =
                PorterDuffColorFilter(ContextCompat.getColor(this.context, color), PorterDuff.Mode.SRC_IN)
        }
    }
}