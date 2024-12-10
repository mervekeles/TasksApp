package com.example.a25nov_plantlist.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a25nov_plantlist.MyApp
import com.example.a25nov_plantlist.R
import com.example.a25nov_plantlist.vm.TaskViewModel
import com.example.a25nov_plantlist.vm.TaskViewModelFactory

class TasksFragment : Fragment() {

    //private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

//        // Initialize Room database and DAO
//        val db = Room.databaseBuilder(
//            requireContext(),
//            TaskDatabase::class.java,
//            "task_database"
//        ).build()
        val db = (requireActivity().application as MyApp).database
        val taskDao = db.taskDao()

        val viewModel: TaskViewModel by activityViewModels {
            TaskViewModelFactory(taskDao)
        }
        // Initialize ViewModel
//        viewModel = ViewModelProvider(this, TaskViewModelFactory(taskDao))
//            .get(TaskViewModel::class.java)

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TaskAdapter()
        recyclerView.adapter = adapter
        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }


        val addTaskButton = view.findViewById<Button>(R.id.add_task)
        addTaskButton.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_addTaskFragment)
        }
        return view
    }
}
