package com.seejiekai.mob21firebase.ui.addEditTodo.base

import androidx.lifecycle.ViewModel
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseManageViewModel: BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    abstract fun submitTask(title: String, desc: String)
}