package com.avijit.warranttrackingsystem.ui.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.FragmentLoginBinding
import com.avijit.warranttrackingsystem.ui.activity.MainDashboardActivity

/**
 * Created by Avijit Acharjee on 12/26/2020 at 3:45 PM.
 * Email: avijitach@gmail.com.
 */
class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginLayout.animation = AnimationUtils.loadAnimation(context, R.anim.top_animation)
        binding.loginButton.setOnClickListener{startActivity(Intent(context,MainDashboardActivity::class.java))}
        /*binding.logoName.setOnClickListener {
            val menu = PopupMenu(context, binding.logoName)

            menu.menu.add("One")
            menu.menu.add("Two")
            menu.menu.add("Three")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                menu.gravity = Gravity.CENTER
            }
            menu.show()
        }*/
    }
}