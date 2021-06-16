package com.example.dicodingjetpackpro.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.dicodingjetpackpro.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val runnable = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        Handler(mainLooper).postDelayed(runnable, 1500L)
    }
}