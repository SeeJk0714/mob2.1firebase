package com.seejiekai.mob21firebase.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.seejiekai.mob21firebase.R
import com.seejiekai.mob21firebase.core.service.StorageService
import com.seejiekai.mob21firebase.databinding.FragmentProfileBinding
import com.seejiekai.mob21firebase.ui.base.BaseFragment
import com.seejiekai.mob21firebase.ui.base.BaseViewModel
import dagger.hilt.android.ActivityRetainedLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val viewModel: ProfileViewModel by viewModels()
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private var imageName: String? = null

    @Inject
    lateinit var storageService: StorageService

    override fun getLayoutResource() = R.layout.fragment_profile

    override fun onBindView(view: View) {
        super.onBindView(view)
        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){
            it?.let { uri ->
                binding?.ivProfile?.setImageURI(uri)
                storageService.uploadImage(uri = it, imageName) { name ->
                    if (name != null) {
                        viewModel.updateProfile(name)
                        imageName = name
                    }
                }
            }
        }

        binding?.ivEditProfile?.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.user.observe(viewLifecycleOwner) {
            imageName = it.profilePic
            binding?.run {
                tvName.text = it.firstName
                tvEmail.text = it.email
                showProfile(ivProfile, imageName)
            }
        }
    }

    private fun showProfile(imageView: ImageView, name: String?) {
        if (name.isNullOrEmpty()) return
        storageService.getImageUri(name) {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.ic_person)
                .centerCrop()
                .into(imageView)
        }
    }


}

/*
    Profile picture, icon button to change the profile picture,
    name, email
 */