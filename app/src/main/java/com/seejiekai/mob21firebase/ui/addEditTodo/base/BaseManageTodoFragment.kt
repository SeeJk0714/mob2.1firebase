package com.seejiekai.mob21firebase.ui.addEditTodo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.seejiekai.mob21firebase.R
import com.seejiekai.mob21firebase.databinding.FragmentManageTodoBinding
import com.seejiekai.mob21firebase.ui.addEditTodo.add.AddTodoViewModel
import com.seejiekai.mob21firebase.ui.base.BaseFragment
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import kotlinx.coroutines.launch

abstract class BaseManageTodoFragment: BaseFragment<FragmentManageTodoBinding>() {
    override val viewModel: BaseManageViewModel by viewModels()

    override fun onBindView(view: View) {
        binding = DataBindingUtil.bind(view)

        binding?.btnSubmit?.setOnClickListener {
            val title = binding?.etTitle?.text.toString()
            val desc = binding?.etDesc?.text.toString()

            viewModel.submitTask(title, desc)
        }
    }

    override fun onBindData(view: View) {
        lifecycleScope.launch {
            viewModel.finish.collect {
                findNavController().popBackStack()
            }
        }
    }
}