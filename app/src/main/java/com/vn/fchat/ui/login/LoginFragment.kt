package com.vn.fchat.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vn.fchat.databinding.FragmentLoginBinding
import com.vn.fchat.ui.utils.hideKeyboardOnClickOutside

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        hideKeyboardOnClickOutside(binding.root)
        return binding.root
    }
}