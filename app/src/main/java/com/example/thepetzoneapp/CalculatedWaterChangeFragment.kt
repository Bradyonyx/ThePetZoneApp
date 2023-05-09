package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thepetzoneapp.databinding.FragmentCalculatedWaterChangeBinding
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding

class CalculatedWaterChangeFragment : Fragment() {
    private var _binding: FragmentCalculatedWaterChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatedWaterChangeBinding.inflate(inflater,container,false)
        return binding.root
    }
}