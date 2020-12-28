package com.avijit.warranttrackingsystem

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.avijit.warranttrackingsystem.databinding.ActivityMainBinding
import com.avijit.warranttrackingsystem.ui.activity.AuthActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var ctx : Context
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this,AuthActivity::class.java))
        },1800)
        ctx = baseContext

        val scaleX : ObjectAnimator = ObjectAnimator.ofFloat(binding.image,"scaleX",0f,1f)
        val scaleY : ObjectAnimator = ObjectAnimator.ofFloat(binding.image,"scaleY",0f,1f)
        scaleX.duration = 1000
        scaleY.duration = 1000
        scaleX.start()
        scaleY.start()
    }
}