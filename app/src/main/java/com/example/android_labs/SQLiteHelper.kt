package com.example.android_labs

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "task_test.db"
        private const val TBL_TASK = "tbl_task"
        private const val ID = "id"
        private const val DESCRIPTION = "description"
        private const val DIFFICULTY = "difficulty"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblTask = ("CREATE TABLE " + TBL_TASK + "("
                + ID + " INTEGER PRIMARY KEY, "
                + DESCRIPTION + " TEXT, "
                + DIFFICULTY + " TEXT)"
                )
        db?.execSQL(createTblTask)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_TASK")
        onCreate(db)
    }

    fun insertTask(task: TaskModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, task.id)
        contentValues.put(DESCRIPTION, task.description)
        contentValues.put(DIFFICULTY, task.difficulty)

        val success = db.insert(TBL_TASK, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllTask(): ArrayList<TaskModel> {
        val taskList: ArrayList<TaskModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_TASK"
        val db = this.writableDatabase
        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var description: String
        var difficulty: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                description = cursor.getString(cursor.getColumnIndex("description"))
                difficulty = cursor.getString(cursor.getColumnIndex("difficulty"))
                val task = TaskModel(id = id, description = description, difficulty = difficulty)
                taskList.add(task)
            } while (cursor.moveToNext())
        }

        return taskList
    }
}