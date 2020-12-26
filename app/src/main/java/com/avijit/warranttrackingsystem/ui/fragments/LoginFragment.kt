package com.avijit.warranttrackingsystem.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avijit.warranttrackingsystem.databinding.FragmentLoginBinding

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
}