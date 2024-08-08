package com.seejiekai.mob21firebase.ui.addEditTodo.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seejiekai.mob21firebase.data.model.Task
import com.seejiekai.mob21firebase.data.repo.TodoRepo
import com.seejiekai.mob21firebase.data.repo.TodoRepoFirestore
import com.seejiekai.mob21firebase.ui.addEditTodo.base.BaseManageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTodoViewModel @Inject constructor(
    private val repo: TodoRepo,
    private val state: SavedStateHandle
): BaseManageViewModel() {
    val task: MutableStateFlow<Task?> = MutableStateFlow(null)
    private val taskId = state.get<String>("taskId")

    init {
        viewModelScope.launch {
            taskId?.let {id ->
                task.value = repo.getTodoById(id)
            }
        }
    }
    override fun submitTask(title: String, desc: String) {
       task.value?.let {
           val newTask = it.copy(title = title, desc = desc)
           viewModelScope.launch {
               repo.updateTask(newTask)
               finish.emit(Unit)
           }
       }
    }
}