package com.avijit.warranttrackingsystem.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.ActivityMainDashboardBinding
import com.avijit.warranttrackingsystem.ui.fragments.AllPendingWarrantsFragment
import com.avijit.warranttrackingsystem.utils.EndDrawerToggle

class MainDashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var endDrawerToggle = EndDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(endDrawerToggle)
        endDrawerToggle.syncState()
        //binding.toolbar.setTitle(R.string.app_name)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.navigationIcon?.setTint(Color.BLACK)
        supportFragmentManager.beginTransaction().replace(R.id.main_dashboard_container, AllPendingWarrantsFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(baseContext,"Back button",Toast.LENGTH_SHORT).show()
        return true
    }
}