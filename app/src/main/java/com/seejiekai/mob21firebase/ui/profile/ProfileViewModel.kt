package com.seejiekai.mob21firebase.ui.profile

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seejiekai.mob21firebase.data.model.Task
import com.seejiekai.mob21firebase.data.model.User
import com.seejiekai.mob21firebase.data.repo.UserRepo
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
) : BaseViewModel() {
    val user = MutableLiveData<User>()

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            errorHandler {
                userRepo.getUser()
            }?.let {
                user.value = it
            }
        }
    }

    fun updateProfile(imageName: String) {
        viewModelScope.launch {
            errorHandler {
                user.value?.let {
                    userRepo.updateUser(it.copy(profilePic = imageName))
                }

            }
        }

    }
}