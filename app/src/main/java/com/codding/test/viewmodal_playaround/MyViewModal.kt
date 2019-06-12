package com.codding.test.viewmodal_playaround

import android.util.Log
import android.view.View
import androidx.databinding.*
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModal(private val userID: Int) : ViewModel(), Observable {
    private var callBacks = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callBacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callBacks.add(callback)
    }

    fun notifyChange() {
        callBacks.notifyCallbacks(this, 0 , null)
    }

    var check = false

    @get:Bindable
    var myPadding = 100

    @Bindable
    fun getSelected() : Boolean {
        return check
    }

    fun setSelected(value: Boolean) {
        Log.d(Constant.LOG_STRING, "setSelected: $value")
        check = value
        callBacks.notifyCallbacks(this, BR.selected , null)
    }




    @get:Bindable
    var name : String = "test"
        set(value) {
            field = value
            callBacks.notifyCallbacks(this, BR.name, null)
        }

    init {
        viewModelScope.launch {
            delay(3000)
            Log.d(Constant.LOG_STRING, "Live data post new value")
            name = "Test1"
            Log.d(Constant.LOG_STRING, "Padding change post new value")
            myPadding = 300
            callBacks.notifyCallbacks(this@MyViewModal, BR.myPadding, null)
        }
    }


    fun executeData() {
        Log.d(Constant.LOG_STRING, "executeData in ViewModal userID: $userID")
    }
}

class BaseViewModalFactory<T>(var creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}