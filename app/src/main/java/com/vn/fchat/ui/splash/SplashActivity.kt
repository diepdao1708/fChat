package com.vn.fchat.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vn.fchat.databinding.ActivitySplashBinding
import com.vn.fchat.ui.AuthActivity
import com.vn.fchat.ui.MainActivity
import com.vn.fchat.ui.utils.setFullScreen
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreen()

        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.events.collect { event ->
                    when (event) {
                        is SplashViewModel.Event.NavigateToLogin -> navigateTo(AuthActivity::class.java)
                        is SplashViewModel.Event.NavigateToHome -> navigateTo(MainActivity::class.java)
                    }
                }
            }
        }


        viewModel.checkLogin()
    }

    private fun <T> navigateTo(cls: Class<T>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, cls)
            startActivity(intent)
            finish()
        }, 1800)
    }
}