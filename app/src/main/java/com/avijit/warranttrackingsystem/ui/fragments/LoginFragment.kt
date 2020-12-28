package com.avijit.warranttrackingsystem.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.avijit.warranttrackingsystem.R
import com.avijit.warranttrackingsystem.databinding.FragmentLoginBinding
import com.avijit.warranttrackingsystem.ui.activity.MainDashboardActivity
import com.avijit.warranttrackingsystem.utils.AppUtils
import com.avijit.warranttrackingsystem.viewmodel.LoginViewModel
import java.util.*


/**
 * Created by Avijit Acharjee on 12/26/2020 at 3:45 PM.
 * Email: avijitach@gmail.com.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding
    private lateinit var progressBar : ProgressBar
    private lateinit var appUtils : AppUtils
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
        appUtils = AppUtils(context)
        binding.loginButton.setOnClickListener{
            appUtils.dialog?.show()
            val requestQueue = Volley.newRequestQueue(context)
            val url = "http://warrant.susmoycse.com/server/oauth/token"
            val stringRequest: StringRequest =
                object : StringRequest(
                    Method.POST,
                    url,
                    Response.Listener { response ->
                        appUtils.dialog?.dismiss()
                        startActivity(Intent(context,MainDashboardActivity::class.java))
                    },
                    Response.ErrorListener {error->
                        appUtils.dialog?.dismiss()
                        Toast.makeText(context, " Incorrect email/password ", Toast.LENGTH_SHORT).show()
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        return super.getHeaders()
                    }

                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> =
                            HashMap()
                        params["client_id"] = "2"
                        params["client_secret"] = "ms7tOMGnTNvZePaoNLjxzjv8RKyictYTMaDEL5xp"
                        params["grant_type"] = "password"
                        params["username"] = binding.userNameEditText.text.toString()
                        params["password"] = binding.passwordEditText.text.toString()

                        return params
                    }
                }
            requestQueue.add(stringRequest)
        }
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