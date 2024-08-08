package com.seejiekai.mob21firebase.data.repo

import com.seejiekai.mob21firebase.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    fun getAllTasks(): Flow<List<Task>>

    suspend fun addTask(task: Task): String?

    suspend fun deleteTask(id: String)

    suspend fun updateTask(task: Task)

    suspend fun getTodoById(id: String): Task?
}
