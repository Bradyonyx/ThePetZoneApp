package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding
import com.example.thepetzoneapp.databinding.FragmentTankListBinding

class TankInfoUserInputFragment : Fragment() {
    private var _binding: FragmentTankInfoUserInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTankInfoUserInputBinding.inflate(inflater,container,false)
        binding.calculateGallonPromptButton.setOnClickListener {
            binding.calculateGallonPromptButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalcGalFragment())
        }
        return binding.root
    }
}