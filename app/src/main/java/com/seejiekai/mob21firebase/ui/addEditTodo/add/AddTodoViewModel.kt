package com.seejiekai.mob21firebase.ui.addEditTodo.add

import androidx.lifecycle.viewModelScope
import com.seejiekai.mob21firebase.data.model.Extra
import com.seejiekai.mob21firebase.data.model.Task
import com.seejiekai.mob21firebase.data.repo.TodoRepo
import com.seejiekai.mob21firebase.data.repo.TodoRepoFirestore
import com.seejiekai.mob21firebase.ui.addEditTodo.base.BaseManageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repo: TodoRepo
): BaseManageViewModel() {

    override fun submitTask(title: String, desc: String) {
        viewModelScope.launch {
            repo.addTask(
                Task(
                    title = title,
                    desc = desc,
                    extra = Extra(location = "Penang", time = "Today"))
            )
            finish.emit(Unit)
        }
    }
}