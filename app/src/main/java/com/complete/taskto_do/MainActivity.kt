package com.complete.taskto_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.complete.taskto_do.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var list : List<TaskTable>
    private lateinit var adapter :TaskRVA
    private lateinit var taskVM:TaskVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<TaskTable>()
        adapter = TaskRVA(list,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true)
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = adapter

        val repoObject = TaskRepo(TaskDatabase(this))
        val factoryObject = TaskVMFactory(repoObject)
        taskVM = ViewModelProvider(this,factoryObject).get(TaskVM::class.java)
        taskVM.getAll().observe(this,{
            adapter.list = it
            for(i in adapter.list.indices){
                Log.d("taget",adapter.list.get(i).taskName)
            }

            adapter.notifyDataSetChanged()

        })


        binding.savebutton.setOnClickListener {
            val inputTask = binding.nexttaskinput.editText?.text
            if(inputTask!!.isNotEmpty()){
                taskVM.insert(TaskTable(inputTask.toString()))
                adapter.notifyDataSetChanged()
                binding.nexttaskinput.editText?.setText("")
            }else{
                Snackbar.make(binding.completedbutton,"You have to enter something",Snackbar.LENGTH_SHORT).show()

            }
        }
        binding.completedbutton.setOnClickListener {
            if(adapter.list.size!=0){
                taskVM.delete(adapter.list.get(0))
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Good!",Toast.LENGTH_SHORT).show()
            }
            else{
                Snackbar.make(binding.completedbutton,"Add Some Tasks First",Snackbar.LENGTH_SHORT).show()
            }


        }



    }

}