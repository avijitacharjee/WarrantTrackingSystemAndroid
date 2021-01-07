package com.avijit.warranttrackingsystem.ui.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.ActivityMainDashboardBinding
import com.avijit.warranttrackingsystem.ui.fragments.AllPendingWarrantsFragment
import com.avijit.warranttrackingsystem.utils.Constants
import com.avijit.warranttrackingsystem.utils.EndDrawerToggle


class MainDashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainDashboardBinding
    private val PERMISSION_REQUEST_CODE = 200
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
        /*supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)*/
        binding.toolbar.navigationIcon?.setTint(Color.BLACK)
        supportFragmentManager.beginTransaction().replace(R.id.main_dashboard_container, AllPendingWarrantsFragment()).commit()
        setupNavigation()
        requestPermission()
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
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION
            ),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0) {
                val fineLocationAccepted =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                val coraseLocationAccepted =
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (fineLocationAccepted && coraseLocationAccepted ) {

                } else {

                    // Snackbar.make(view, "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)
                            || shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION)
                        ) {
                            showMessageOKCancel("You need to allow access to the permissions",
                                DialogInterface.OnClickListener { dialog, which -> requestPermission() })
                            return
                        }
                    }
                }
            }
        }
    }

    private fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton(
                "Cancel"
            ) { dialog, which -> finishAffinity() }
            .create()
            .show()
    }
}