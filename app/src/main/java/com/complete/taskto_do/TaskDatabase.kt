package com.complete.taskto_do

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskTable::class],version = 2)
abstract class TaskDatabase  : RoomDatabase() {
    abstract fun getDao():TaskDao

    companion object{
        @Volatile
        private var instance  : TaskDatabase? = null
        private val lock = Any()

        operator fun invoke(context : Context) = instance?: synchronized(lock){
            instance?:createDatabase(context).also{
                instance = it
            }
        }
        private fun createDatabase(context: Context) = Room
            .databaseBuilder(context.applicationContext,TaskDatabase::class.java,"tasks")
            .build()
    }



}