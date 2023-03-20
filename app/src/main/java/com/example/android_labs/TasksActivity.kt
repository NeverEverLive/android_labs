package com.example.android_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class TasksActivity : AppCompatActivity() {
    private lateinit var edDescription: EditText
    private lateinit var edDifficulty: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: TaskAdapter? = null
    private lateinit var menu_show: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(1)
        setContentView(R.layout.activity_tasks)
        println(1)

        initView()
        initRecycleView()

        sqliteHelper = SQLiteHelper(this)
        btnAdd.setOnClickListener{ addTask() }
        btnView.setOnClickListener{ getTask() }
    }

    private fun getTask() {
        val taskList = sqliteHelper.getAllTask()
        Log.e("pppppp", "${taskList.size}")
        adapter?.addItems(taskList)
    }

    private fun addTask() {
        val description = edDescription.text.toString()
        val difficulty = edDifficulty.text.toString()
        println(123)
        println(description)
        println(difficulty)
        println(description.isEmpty())

        if (description.isEmpty() || difficulty.isEmpty()){
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val std = TaskModel(description = description, difficulty = difficulty)
            val status = sqliteHelper.insertTask(std)
            if (status > -1) {
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun clearEditText() {
        edDescription.setText("")
        edDifficulty.setText("")
        edDescription.requestFocus()
    }

    private fun initRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        println("init")
        edDescription = findViewById(R.id.edDescription)
        edDifficulty = findViewById(R.id.edDifficulty)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        recyclerView = findViewById(R.id.recyclerView)
        menu_show = findViewById(R.id.show_menu)
    }

//    fun onClickMain(view: View) {
//        setContentView(R.layout.activity_main)
////        moveTaskToBack(true);
////        exitProcess(-1)
//    }
}