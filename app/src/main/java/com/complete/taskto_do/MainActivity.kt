package com.complete.taskto_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.complete.taskto_do.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var list : List<TaskTable>
    private lateinit var adapter :TaskRVA
    private lateinit var taskVM:TaskVM
    private var isOn : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<TaskTable>()
        adapter = TaskRVA(list,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true)
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = adapter
        if(list.isNotEmpty()){
           binding.addsometask.visibility = View.INVISIBLE
        }
        //for drag and sort
        var items = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                val position = viewHolder.adapterPosition
                val targetp = target.adapterPosition

                taskVM.update(TaskTable(adapter.list.get(position).taskName,adapter.list.get(targetp).taskNumber))
                taskVM.update(TaskTable(adapter.list.get(targetp).taskName,adapter.list.get(position).taskNumber))


                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskVM.delete(adapter.list.get(viewHolder.adapterPosition))
                for(i in viewHolder.adapterPosition..adapter.list.size-1){
                    taskVM.update(TaskTable(adapter.list.get(i).taskName,(adapter.list.get(i).taskNumber.toInt()-1).toString()))
                }
                Toast.makeText(this@MainActivity,"Deleted",Toast.LENGTH_SHORT).show()

            }

        }

        val item = ItemTouchHelper(items)
        item.attachToRecyclerView(binding.recyclerView)

        val repoObject = TaskRepo(TaskDatabase(this))
        val factoryObject = TaskVMFactory(repoObject)

        taskVM = ViewModelProvider(this,factoryObject).get(TaskVM::class.java)
        taskVM.getAll().observe(this,{
            adapter.setData(it)
        })


        binding.savebutton.setOnClickListener {
            val inputTask = binding.nexttaskinput.editText?.text
            if(inputTask!!.isNotEmpty()){
                taskVM.insert(TaskTable(inputTask.toString(),(adapter.list.size+1).toString()))
                adapter.setData(adapter.list)
                binding.nexttaskinput.editText?.setText("")
            }else{
                Snackbar.make(binding.completedbutton,"You have to enter something",Snackbar.LENGTH_SHORT).show()

            }
        }
        binding.completedbutton.setOnClickListener {
            if(adapter.list.size!=0){
                taskVM.delete(adapter.list.get(0))
                for(i in adapter.list){
                    taskVM.update(TaskTable(i.taskName,(i.taskNumber.toInt()-1).toString()))
                }
                adapter.setData(list)
                Toast.makeText(this,"Good!",Toast.LENGTH_SHORT).show()
            }
            else{
                Snackbar.make(binding.completedbutton,"Add Some Tasks First",Snackbar.LENGTH_SHORT).show()
            }


        }



    }
    /*<svg xmlns="http://www.w3.org/2000/svg" width="404" height="108" viewBox="0 0 404 108">
  <g id="Rectangle_1" data-name="Rectangle 1" fill="#9100ff" stroke="#fff" stroke-width="3">
    <rect width="404" height="108" rx="20" stroke="none"/>
    <rect x="1.5" y="1.5" width="401" height="105" rx="18.5" fill="none"/>
  </g>
</svg>
*/

}