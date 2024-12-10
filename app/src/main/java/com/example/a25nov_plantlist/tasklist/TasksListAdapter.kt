package com.example.a25nov_plantlist.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a25nov_plantlist.databinding.TaskItemLayoutBinding
import com.example.a25nov_plantlist.db.Task

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(DiffCallback()) {

    class TaskViewHolder(private val binding: TaskItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        //  class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        // val titleTextView: TextView = view.findViewById(R.id.taskTitle)
        //val descriptionTextView: TextView = view.findViewById(R.id.taskDesc)
        fun bind(task:Task) {
            binding.task = task
            binding.executePendingBindings()
        }

        companion object {

        fun from(parent: ViewGroup): TaskViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            val binding = TaskItemLayoutBinding.inflate(layoutInflater, parent, false)
            return TaskViewHolder(binding)
        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item_layout, parent, false)
        //return TaskViewHolder(view)
        return TaskViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        //holder.titleTextView.text = task.title
        //holder.descriptionTextView.text = task.description
        holder.bind(task)
    }

    //override fun getItemCount(): Int = tasks.size
}
class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}