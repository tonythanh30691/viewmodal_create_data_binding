package com.codding.test.viewmodal_playaround

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

object BindingClass {
    @BindingAdapter("android:paddingLeft")
    @JvmStatic
    fun setPaddingLeft(view: View, oldPadding : Int, newPadding : Int) {
        Log.d(Constant.LOG_STRING, "setPaddingLeft: $oldPadding $newPadding")
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, view.paddingTop, view.paddingRight, view.paddingBottom)
        }
    }


}