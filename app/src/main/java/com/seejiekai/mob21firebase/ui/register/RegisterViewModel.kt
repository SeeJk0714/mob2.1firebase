package com.seejiekai.mob21firebase.ui.register

import androidx.lifecycle.viewModelScope
import com.seejiekai.mob21firebase.core.service.AuthService
import com.seejiekai.mob21firebase.core.utils.ValidationUtil
import com.seejiekai.mob21firebase.data.model.User
import com.seejiekai.mob21firebase.data.model.ValidationField
import com.seejiekai.mob21firebase.data.repo.UserRepo
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService,
    private val userRepo: UserRepo
): BaseViewModel() {
    val success: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun createUser(firstName: String, lastName: String, email: String, pass: String, confirmPass: String) {
        val error = ValidationUtil.validate(
            ValidationField(firstName, "[a-zA-z ]{2,20}", "Enter a valid name"),
            ValidationField(lastName, "[a-zA-z ]{2,20}", "Enter a valid name"),
            ValidationField(email, "[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+", "Enter a valid email"),
            ValidationField(pass, "[a-zA-z0-9#$%]{3,20}", "Enter a valid password")
        )

        if (error == null){
            viewModelScope.launch(Dispatchers.IO) {
                errorHandler {
                    authService.createUserWithEmailAndPass(email, pass)
                }?.let {
                    userRepo.createUser(
                        User(firstName, lastName, email)
                    )
                    success.emit(Unit)
                }
            }
        } else {
            viewModelScope.launch {
                _error.emit(error)
            }
        }

    }
//
//    private fun validate(firstName: String, lastName: String, email: String, pass: String, confirmPass: String) {
//        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
//            throw Exception("Name, Email, password & confirm password cannot be empty")
//        }else if (pass != confirmPass) {
//            throw Exception("Password and confirm password not correct")
//        }
//    }
}