package com.example.polyv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.polyv1.MainFragments.*
import com.example.polyv1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_frame, BasketFragment()).commit()
        }
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, MainFragment())
                        .commit()
                }
                R.id.more -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, CatalogFragment())
                        .commit()
                }
                R.id.bell -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, NotificationFragment())
                        .commit()
                }
                R.id.cart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, BasketFragment())
                        .commit()
                }
                R.id.setting -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, SettingsFragment())
                        .commit()
                }
            }
            true
        }

    }
}