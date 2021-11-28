package com.complete.taskto_do

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskVMFactory(private val task : TaskRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskVM(task) as T
    }
}