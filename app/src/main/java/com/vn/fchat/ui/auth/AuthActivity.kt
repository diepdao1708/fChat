package com.vn.fchat.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vn.fchat.R
import com.vn.fchat.data.api.response.LoadingStatus
import com.vn.fchat.data.model.User
import com.vn.fchat.databinding.ActivityAuthBinding
import com.vn.fchat.ui.MainActivity
import com.vn.fchat.ui.utils.hideKeyboardOnClickOutside
import com.vn.fchat.ui.utils.setFullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        viewModel.loginResult.observe(this) {
            when (it.loadingStatus) {
                LoadingStatus.Error -> Toast.makeText(
                    this,
                    getString(R.string.auth__login_fail),
                    Toast.LENGTH_LONG,
                ).show()
                LoadingStatus.Success -> navToHome()
                else -> null
            }
        }

        hideKeyboardOnClickOutside(binding.root)
        setFullScreen()
    }

    private fun navToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}