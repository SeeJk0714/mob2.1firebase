package com.seejiekai.mob21firebase.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.seejiekai.mob21firebase.R
import com.seejiekai.mob21firebase.databinding.FragmentLoginBinding
import com.seejiekai.mob21firebase.databinding.FragmentRegisterBinding
import com.seejiekai.mob21firebase.ui.base.BaseFragment
import com.seejiekai.mob21firebase.ui.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_register

    override fun onBindView(view: View) {
        super.onBindView(view)

        binding?.btnLogin?.setOnClickListener {
            findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }

        binding?.run {
            btnRegister.setOnClickListener {
                viewModel.createUser(
                    firstName= etFirstName.text.toString(),
                    lastName= etLastName.text.toString(),
                    email = etEmail.text.toString(),
                    pass = etPassword.text.toString(),
                    confirmPass = etConfirmPassword.text.toString()
                )
            }
        }

    }
    override fun onBindData(view: View) {
        super.onBindData(view)

        lifecycleScope.launch {
            viewModel.success.collect {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                )
            }
        }
    }
}