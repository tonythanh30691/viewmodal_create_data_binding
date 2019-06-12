package com.codding.test.viewmodal_playaround

import androidx.lifecycle.ViewModel
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders


inline fun <reified T : ViewModel> FragmentActivity.createViewModal(noinline creator : ( () -> T )? = null) : T {
    return if (creator == null) {
        ViewModelProviders.of(this).get(T::class.java)
    } else {
        ViewModelProviders.of(this, BaseViewModalFactory (creator)).get(T::class.java)
    }
}