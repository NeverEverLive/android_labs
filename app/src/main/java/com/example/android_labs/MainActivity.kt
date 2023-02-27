package com.example.android_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickTasks(view: View) {
        setContentView(R.layout.activity_tasks)
        Toast.makeText(this, "You're signed in", Toast.LENGTH_SHORT).show()
//        NotificationHelper(this, "Test message").Notification()
    }
}