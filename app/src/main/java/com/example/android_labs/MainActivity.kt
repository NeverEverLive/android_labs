package com.example.android_labs

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("MissingInflatedId")
    fun onClickTasks(view: View) {
        val button: Button = findViewById(R.id.edSubmit)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, TasksActivity::class.java)
            Toast.makeText(this, "You're signed in", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
//        NotificationHelper(this, "Test message").Notification()
    }
}