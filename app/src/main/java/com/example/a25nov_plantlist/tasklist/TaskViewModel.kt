package com.example.a25nov_plantlist.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a25nov_plantlist.db.Task
import com.example.a25nov_plantlist.db.TaskDao
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val allTasks = taskDao.getAllTasks().asLiveData() // Observe changes in tasks

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            taskDao.insertTask(Task(title = title, description = description))
        }
    }
}

// Factory to provide TaskDao to the ViewModel
class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
