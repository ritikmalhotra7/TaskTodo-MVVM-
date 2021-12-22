package com.complete.taskto_do

import androidx.recyclerview.widget.DiffUtil

class TaskDiffUtils(
    private val oldList : List<TaskTable>,
    private val newList : List<TaskTable>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].itemKey == newList[newItemPosition].itemKey
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return when{
           oldList[oldItemPosition].itemKey != newList[newItemPosition].itemKey -> {
               false
           }
           oldList[oldItemPosition].taskNumber != newList[newItemPosition].taskNumber -> {
               false
           }
           oldList[oldItemPosition].taskName != newList[newItemPosition].taskName -> {
               false
           }
           else -> true
       }
    }
}