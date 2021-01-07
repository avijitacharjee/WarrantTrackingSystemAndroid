package com.avijit.warranttrackingsystem.utils

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.LocationManager
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast


/**
 * Created by Avijit Acharjee on 12/28/2020 at 6:59 PM.
 * Email: avijitach@gmail.com.
 */
public class AppUtils(private var context: Context?) {
    var dialog: AlertDialog ?= null
    var sf : SharedPreferences?=null
    var locationManager : LocationManager?=null
    init {
        setProgressDialog()
        setSharedPreferences()
    }
    private fun setProgressDialog() {
        val llPadding = 30
        val ll = LinearLayout(context)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        ll.layoutParams = llParam
        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam
        llParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(context)
        tvText.text = "Loading ..."
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20f
        tvText.layoutParams = llParam
        ll.addView(progressBar)
        ll.addView(tvText)
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(ll)
        val dialog : AlertDialog
        dialog = builder.create()
        dialog.setCancelable(false)
        val window: Window? = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window!!.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = layoutParams
        }
        this.dialog = dialog
    }

    fun setSharedPreferences(){
        sf = context?.getSharedPreferences(Constants.SHARED_PREFERENCES,Context.MODE_PRIVATE)
    }
    fun showRedToast(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        val view: View? = toast.view

        //Gets the actual oval background of the Toast then sets the colour filter
        view?.getBackground()?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)

        //Gets the TextView from the Toast so it can be editted
        val text: TextView? = view?.findViewById(R.id.message)
        text?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        text?.setTextColor(Color.WHITE)
        toast.show()
    }
}