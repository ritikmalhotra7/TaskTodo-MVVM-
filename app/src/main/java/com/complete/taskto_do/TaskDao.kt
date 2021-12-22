package com.complete.taskto_do

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task : TaskTable)
    @Delete
    suspend fun delete(task:TaskTable)
    @Query("Select * from tasks order by taskNumber")
    fun getAllTasks(): LiveData<List<TaskTable>>
    @Query("update tasks set taskNumber = :taskN where taskName = :taskName")
    fun setTask(taskName :String,taskN :String)
    @Query("update tasks set taskNumber = taskNumber-1")
    fun minus1()

}