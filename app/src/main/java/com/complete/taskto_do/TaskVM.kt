package com.complete.taskto_do

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskVM (val taskRepo :TaskRepo): ViewModel() {
    fun insert(task : TaskTable) = GlobalScope.launch{
        taskRepo.insert(task)
    }
    fun delete(task:TaskTable) = GlobalScope.launch {
        taskRepo.delete(task)
    }
    fun getAll() = taskRepo.getall()
    fun update(task: TaskTable) = GlobalScope.launch {
        taskRepo.update(task)
    }
    fun minus1() = taskRepo.minus1()

}