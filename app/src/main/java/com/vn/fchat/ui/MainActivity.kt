package com.vn.fchat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.vn.fchat.R
import com.vn.fchat.databinding.ActivityMainBinding
import com.vn.fchat.ui.utils.setKeyboardVisibilityListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragment_activity_main) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
            setKeyboardVisibilityListener { visibility ->
                binding.bottomNavigationView.isVisible = !visibility
            }
        }
    }
}