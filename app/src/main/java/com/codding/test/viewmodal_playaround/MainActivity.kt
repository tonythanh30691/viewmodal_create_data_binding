package com.codding.test.viewmodal_playaround

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.codding.test.viewmodal_playaround.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModal by lazy {
        Log.d(Constant.LOG_STRING, "viewmodal lazy init")
        createViewModal {MyViewModal(2)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityMainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.viewModal = viewModal


    }

    override fun onStart() {
        super.onStart()
        viewModal.executeData()
    }

    override fun onResume() {
        super.onResume()

        viewModal.executeData()

        var person = Person("thanh", 2).apply {
            name = "test"
            age = 3
        }

        Log.d(Constant.LOG_STRING, "person also: $person")

        var person2 = Person("thanh", 2).also {
            it.name = "test1"
            it.age = 4
        }

        Log.d(Constant.LOG_STRING, "person2 also: $person2")

        var numberList = mutableListOf<Int>()
        numberList.also {    Log.d(Constant.LOG_STRING, "Prepare to add list") }
            .apply {
                add(3)
                add(2)
                add(9)
            }
            .also { Log.d(Constant.LOG_STRING, "Prepare to sort list") }
            .sort()
          Log.d(Constant.LOG_STRING, "Result list: $numberList")

//        val numbers = mutableListOf("one", "two", "three")
//        val countEndsWithE = numbers.run {
//            add("four")
//            add("five")
//            count { it.endsWith("e") }
//        }
//        Log.d(Constant.LOG_STRING,"There are $countEndsWithE elements that end with e.")
//        Log.d(Constant.LOG_STRING,"numbers: $numbers")

        val numbers = mutableListOf("one", "two", "three", "four", "five")
        numbers.map { it.length }.filter { it > 3 }.let {
            Log.d(Constant.LOG_STRING, "Result: $it")
            // and more function calls if needed
        }
    }
}
