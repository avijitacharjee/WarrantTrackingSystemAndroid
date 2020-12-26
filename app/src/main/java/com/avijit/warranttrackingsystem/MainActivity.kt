package com.avijit.warranttrackingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.avijit.warranttrackingsystem.databinding.ActivityMainBinding
import com.avijit.warranttrackingsystem.ui.activity.AuthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this,AuthActivity::class.java))
        },2000)
    }
}