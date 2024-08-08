package com.seejiekai.mob21firebase.ui.login

import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.seejiekai.mob21firebase.core.service.AuthService
import com.seejiekai.mob21firebase.data.model.User
import com.seejiekai.mob21firebase.data.repo.TodoRepo
import com.seejiekai.mob21firebase.data.repo.TodoRepoFirestore
import com.seejiekai.mob21firebase.data.repo.UserRepo
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val authService: AuthService
): BaseViewModel() {
    val success: MutableSharedFlow<Unit> = MutableSharedFlow()

    init {
        getGreetings()
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            errorHandler {
                validate(email, pass)
                authService.loginWithEmailAndPass(email, pass)
            }?.let {
                success.emit(Unit)
            }
        }
    }

    fun login(credential: GoogleIdTokenCredential) {
        viewModelScope.launch {
            authService.signInWithGoogle(credential)
            userRepo.createUser(
                User(credential.displayName ?: "", "", credential.id)
            )
            success.emit(Unit)
        }

    }

    private fun validate(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            throw Exception("Email or password cannot be empty")
        }
    }

    private fun getGreetings() {
        viewModelScope.launch {
            errorHandler {
//                repo.getGreetings()
            }?.let {
//                Log.d("debugging", it.toString())
            }
        }
    }
}