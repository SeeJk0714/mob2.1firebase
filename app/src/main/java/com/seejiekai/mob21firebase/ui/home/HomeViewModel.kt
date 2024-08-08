package com.seejiekai.mob21firebase.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.seejiekai.mob21firebase.core.service.AuthService
import com.seejiekai.mob21firebase.data.repo.TodoRepo
import com.seejiekai.mob21firebase.data.repo.TodoRepoFirestore
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var repo: TodoRepo,
    private val authService: AuthService
): BaseViewModel() {

    init {
        viewModelScope.launch {
            errorHandler {
//                repo.getAllTaskByTitle("ijk")
            }?.let {
                Log.d("debugging", it.toString())
            }
        }
    }

    fun getAllTasks() = repo.getAllTasks()

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            errorHandler { repo.deleteTask(taskId) }
//            errorHandler(func = {repo.deleteTask(taskId)})
        }
    }
}