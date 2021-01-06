package com.avijit.warranttrackingsystem.ui.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.ActivityAuthBinding
import com.avijit.warranttrackingsystem.ui.fragments.LoginFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().replace(R.id.auth_container,LoginFragment()).commit()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit.")
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                finishAffinity()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

            })
            .create()
            .show()
    }
}