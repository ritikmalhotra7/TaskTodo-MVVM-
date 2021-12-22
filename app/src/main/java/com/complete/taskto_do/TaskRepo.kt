package com.complete.taskto_do

import android.util.Log

class TaskRepo (val db : TaskDatabase){
    suspend fun insert(task : TaskTable) = db.getDao().insert(task)
    suspend fun delete(task :TaskTable) = db.getDao().delete(task)
    fun getall() = db.getDao().getAllTasks()
    fun update(task:TaskTable) = db.getDao().setTask(task.taskName,task.taskNumber)
    fun minus1() = db.getDao().minus1()
}