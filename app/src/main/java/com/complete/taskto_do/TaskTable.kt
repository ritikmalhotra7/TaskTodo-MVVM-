package com.complete.taskto_do

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskTable (
    @ColumnInfo(name = "taskName")
    var taskName : String
){
    @PrimaryKey(autoGenerate = true)
    var itemKey : Int? = null
}