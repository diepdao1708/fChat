package com.vn.fchat.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vn.fchat.data.api.response.DataResponse
import com.vn.fchat.data.api.response.LoadingStatus
import com.vn.fchat.data.model.User
import com.vn.fchat.databinding.FragmentLoginBinding
import com.vn.fchat.ui.utils.hideKeyboardOnClickOutside
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        observe()
        hideKeyboardOnClickOutside(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.loginBtn.setOnClickListener {
            viewModel.checkLogin(
                User(
                    "",
                    binding.phoneEditTxt.text.toString(),
                    binding.passwordEditTxt.text.toString()
                )
            )
        }
    }

    private fun observe() {
        viewModel.loginResult.observe(viewLifecycleOwner) {
            it?.let {
                if (it.loadingStatus == LoadingStatus.Success) {
                    val result = (it as DataResponse.DataSuccess).body
                    Toast.makeText(
                        requireContext(),
                        "${result.message} ${result.token}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}