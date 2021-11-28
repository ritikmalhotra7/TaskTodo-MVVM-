package com.complete.taskto_do

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.complete.taskto_do.databinding.CardviewtraepBinding

class TaskRVA(var list :List<TaskTable>, private val ctx : Context) : RecyclerView.Adapter<TaskRVA.TaskVH>() {
    class TaskVH(val binding:CardviewtraepBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val b = CardviewtraepBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskVH(b)
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        holder.binding.task.text = list.get(position).taskName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}