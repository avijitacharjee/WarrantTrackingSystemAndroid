package com.avijit.warranttrackingsystem.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.avijit.warranttrackingsystem.R

class EndDrawerToggle(
    activity: Activity, private val drawerLayout: DrawerLayout, toolbar: Toolbar,
    openDrawerContentDescRes: Int, closeDrawerContentDescRes: Int
) :
    DrawerListener {
    private val arrowDrawable: DrawerArrowDrawable
    private val toggleButton: AppCompatImageButton
    private val openDrawerContentDesc: String
    private val closeDrawerContentDesc: String
    fun syncState() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            setPosition(1f)
        } else {
            setPosition(0f)
        }
    }

    fun toggle() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    fun setPosition(position: Float) {
        if (position == 1f) {
            arrowDrawable.setVerticalMirror(true)
            toggleButton.contentDescription = closeDrawerContentDesc
        } else if (position == 0f) {
            arrowDrawable.setVerticalMirror(false)
            toggleButton.contentDescription = openDrawerContentDesc
        }
        arrowDrawable.progress = position
    }

    override fun onDrawerSlide(
        drawerView: View,
        slideOffset: Float
    ) {
        setPosition(Math.min(1f, Math.max(0f, slideOffset)))
    }

    override fun onDrawerOpened(drawerView: View) {
        setPosition(1f)
    }

    override fun onDrawerClosed(drawerView: View) {
        setPosition(0f)
    }

    override fun onDrawerStateChanged(newState: Int) {}

    init {
        openDrawerContentDesc = activity.getString(openDrawerContentDescRes)
        closeDrawerContentDesc = activity.getString(closeDrawerContentDescRes)
        arrowDrawable = DrawerArrowDrawable(toolbar.context)
        arrowDrawable.direction = DrawerArrowDrawable.ARROW_DIRECTION_END
        arrowDrawable.color = Color.BLACK
        toggleButton = AppCompatImageButton(
            toolbar.context, null,
            R.attr.toolbarNavigationButtonStyle
        )
        toolbar.addView(
            toggleButton,
            Toolbar.LayoutParams(GravityCompat.END)
        )
        toggleButton.setImageDrawable(arrowDrawable)
        toggleButton.setOnClickListener { toggle() }
    }
}