package com.example.a25nov_plantlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.a25nov_plantlist.tasklist.TaskViewModel
import com.example.a25nov_plantlist.tasklist.TaskViewModelFactory

class AddTaskFragment : Fragment() {

    // Shared ViewModel scoped to the Activity
    private val viewModel: TaskViewModel by activityViewModels() {
        TaskViewModelFactory((requireActivity().application as MyApp).database.taskDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        val titleEditText = view.findViewById<EditText>(R.id.edit_text_title)
        val descriptionEditText = view.findViewById<EditText>(R.id.edit_text_description)
        val addButton = view.findViewById<Button>(R.id.button_add_task)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (title.isNotBlank() && description.isNotBlank()) {
                viewModel.addTask(title, description)
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Please enter both title and description", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
