package com.avijit.warranttrackingsystem.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avijit.warranttrackingsystem.databinding.FragmentExecutedWarrantsBinding

/**
 * Created by Avijit Acharjee on 1/4/2021 at 10:39 AM.
 * Email: avijitach@gmail.com.
 */
class ExecutedWarrantsFragment : Fragment() {
    private lateinit var binding : FragmentExecutedWarrantsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExecutedWarrantsBinding.inflate(inflater,container,false)
        return binding.root
    }
}