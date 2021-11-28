package com.complete.taskto_do

class TaskRepo (val db : TaskDatabase){
    suspend fun insert(task : TaskTable) = db.getDao().insert(task)
    suspend fun delete(task :TaskTable) = db.getDao().delete(task)
    fun getall() = db.getDao().getAllTasks()
}