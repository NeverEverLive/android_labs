package com.example.android_labs

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import kotlin.system.exitProcess
import retrofit2.Callback
import retrofit2.Response

class TasksActivity : AppCompatActivity() {
//    private lateinit var edDescription: EditText
//    private lateinit var edDifficulty: EditText
//    private lateinit var btnAdd: Button
//    private lateinit var btnView: Button
//    private lateinit var sqliteHelper: SQLiteHelper
//    private lateinit var recyclerView: RecyclerView
    private lateinit var APIrecyclerView: RecyclerView
    private var adapter: TaskAdapter? = null
    private lateinit var menu_show: Button
    private lateinit var btnExit: Button
    private lateinit var alert_builder: AlertDialog.Builder
//    private lateinit var menu_builder: MenuBuilder
//    private lateinit var inflater: MenuInflater

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(1)
        setContentView(R.layout.activity_tasks)
        println(1)

        initView()
//        initRecycleView()
        initAPIRecycleView()
//        menu_builder = MenuBuilder(this)
//        inflater = MenuInflater(this)

//        sqliteHelper = SQLiteHelper(this)
//        btnAdd.setOnClickListener{ addTask() }
//        btnView.setOnClickListener{ getTask() }
        registerForContextMenu(menu_show)
//        btnExit.setOnClickListener{
//            alert_builder.setTitle("Alert!")
//                .setMessage("Do you want to exit?")
//                .setCancelable(true)
//                .setPositiveButton("yes"){dialogInterface,it ->
//                    val intent = Intent(this@TasksActivity, MainActivity::class.java)
//                    Toast.makeText(this, "You're logged out", Toast.LENGTH_SHORT).show()
//                    startActivity(intent)
//                }
//                .setNegativeButton("no"){dialogInterface,it ->
//                    dialogInterface.cancel()
//                }
//                .show()
//        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        println(123123)
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.popmenu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exit -> {
                println("t1")
                val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
                val call = serviceGenerator.getPosts()
                println("t2")

                call.enqueue(object: Callback<MutableList<PostModel>>{
                    override fun onResponse(
                        call: Call<MutableList<PostModel>>,
                        response: Response<MutableList<PostModel>>
                    ) {
                        if(response.isSuccessful) {
                            APIrecyclerView.apply {
                                layoutManager = LinearLayoutManager(this@TasksActivity)
                                adapter = PostAdapter(response.body()!!)
                            }
                            println("SUCCESS")
                        }
                    }

                    override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("ERROR", t.message.toString())
                    }
                })
            }
        }
        return super.onContextItemSelected(item)
    }

//    private fun getTask() {
//        val taskList = sqliteHelper.getAllTask()
//        Log.e("pppppp", "${taskList.size}")
//        adapter?.addItems(taskList)
//    }
//
//    private fun addTask() {
//        val description = edDescription.text.toString()
//        val difficulty = edDifficulty.text.toString()
//        println(123)
//        println(description)
//        println(difficulty)
//        println(description.isEmpty())
//
//        if (description.isEmpty() || difficulty.isEmpty()){
//            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
//        } else {
//            val std = TaskModel(description = description, difficulty = difficulty)
//            val status = sqliteHelper.insertTask(std)
//            if (status > -1) {
//                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
//                clearEditText()
//            } else {
//                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//
//    private fun clearEditText() {
//        edDescription.setText("")
//        edDifficulty.setText("")
//        edDescription.requestFocus()
//    }
//
//    private fun initRecycleView() {
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = TaskAdapter()
//        recyclerView.adapter = adapter
//    }

    private fun initAPIRecycleView() {
        APIrecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter()
        APIrecyclerView.adapter = adapter
    }

    private fun initView() {
        println("init")
//        edDescription = findViewById(R.id.edDescription)
//        edDifficulty = findViewById(R.id.edDifficulty)
//        btnAdd = findViewById(R.id.btnAdd)
//        btnView = findViewById(R.id.btnView)
//        btnExit = findViewById(R.id.btnExit)
//        recyclerView = findViewById(R.id.recyclerView)
        APIrecyclerView = findViewById(R.id.APIrecyclerView)
        menu_show = findViewById(R.id.show_menu)
//        alert_builder = AlertDialog.Builder(this)
    }

//    fun onClickMain(view: View) {
//        setContentView(R.layout.activity_main)
////        moveTaskToBack(true);
////        exitProcess(-1)
//    }
}