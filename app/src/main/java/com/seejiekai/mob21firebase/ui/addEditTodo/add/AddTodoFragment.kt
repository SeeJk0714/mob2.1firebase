package com.seejiekai.mob21firebase.ui.addEditTodo.add

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.seejiekai.mob21firebase.R
import com.seejiekai.mob21firebase.databinding.FragmentManageTodoBinding
import com.seejiekai.mob21firebase.ui.addEditTodo.base.BaseManageTodoFragment
import com.seejiekai.mob21firebase.ui.addEditTodo.base.BaseManageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : BaseManageTodoFragment() {
    override val viewModel: AddTodoViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_manage_todo
}