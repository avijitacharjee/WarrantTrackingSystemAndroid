package com.avijit.warranttrackingsystem.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.ActivityMainDashboardBinding
import com.avijit.warranttrackingsystem.ui.fragments.AllPendingWarrantsFragment
import com.avijit.warranttrackingsystem.utils.Constants
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
        setupNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(baseContext,"Back button",Toast.LENGTH_SHORT).show()
        return true
    }

    private fun setupNavigation(){
        binding.navView.setNavigationItemSelectedListener {
            var id : Int = it.itemId
            if(id==R.id.nav_logout){
                getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit().clear().apply()
                startActivity(Intent(baseContext,AuthActivity::class.java))
            }
            true
        }
    }
}