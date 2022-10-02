package com.vn.fchat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vn.fchat.databinding.ActivityAuthBinding
import com.vn.fchat.ui.utils.setFullScreen

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreen()
    }
}