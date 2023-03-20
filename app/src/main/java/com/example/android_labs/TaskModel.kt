package com.example.android_labs

import java.util.*

class TaskModel(
    var id: Int = getAutoID(),
    var description: String = "",
    var difficulty: String = "",
) {
    companion object {
        fun getAutoID(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }

}