package com.seejiekai.mob21firebase.data.repo

import com.seejiekai.mob21firebase.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTodoRepoImpl: TodoRepo {
    override fun getAllTasks() = flow<List<Task>> {
        emit(emptyList())
    }

    override suspend fun addTask(task: Task): String? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun getTodoById(id: String): Task? {
        TODO("Not yet implemented")
    }
}