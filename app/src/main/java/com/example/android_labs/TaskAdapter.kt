package com.example.android_labs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: ArrayList<TaskModel> = ArrayList()

    fun addItems(items: ArrayList<TaskModel>) {
        this.taskList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_item_task, parent, false)
    )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindView(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class TaskViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var description = view.findViewById<TextView>(R.id.tvDescription)
        private var difficulty = view.findViewById<TextView>(R.id.tvDifficulty)
        private var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(task: TaskModel) {
            id.text = task.id.toString()
            description.text = task.description
            difficulty.text = task.difficulty
        }
    }


}