package com.seejiekai.mob21firebase.ui.addEditTodo.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seejiekai.mob21firebase.R
import com.seejiekai.mob21firebase.ui.addEditTodo.base.BaseManageTodoFragment
import com.seejiekai.mob21firebase.ui.addEditTodo.base.BaseManageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditTodoFragment: BaseManageTodoFragment() {
    override val viewModel: EditTodoViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_manage_todo

    override fun onBindView(view: View) {
        super.onBindView(view)
        binding?.btnSubmit?.text = getString(R.string.update)
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.task.collect {
                if (it != null){
                    binding?.etTitle?.setText(it.title)
                    binding?.etDesc?.setText(it.desc)
                }
            }
        }
    }
}