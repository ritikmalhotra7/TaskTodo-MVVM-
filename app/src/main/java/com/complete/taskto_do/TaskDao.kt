package com.complete.taskto_do

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task : TaskTable)
    @Delete
    suspend fun delete(task:TaskTable)
    @Query("Select * from tasks")
    fun getAllTasks():LiveData<List<TaskTable>>
}