package com.example.android_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.system.exitProcess

class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
    }

    fun onClickMain(view: View) {
        setContentView(R.layout.activity_main)
//        moveTaskToBack(true);
//        exitProcess(-1)
    }
}