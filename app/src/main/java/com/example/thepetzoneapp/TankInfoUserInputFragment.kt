package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding
import com.example.thepetzoneapp.databinding.FragmentTankListBinding

class TankInfoUserInputFragment : Fragment() {
    private var _binding: FragmentTankInfoUserInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TankViewModel by viewModels()
    //val tankNum = viewModel.tankNum

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTankInfoUserInputBinding.inflate(inflater,container,false)
//        viewModel.observableTankList.observe(viewLifecycleOwner, Observer {tankSize ->
//            if(viewModel.tankList[tankNum].tankSize != 0.0) {
//                binding.tankSizeInput.setVisibility(INVISIBLE)
//                binding.tankSizeCalculated.text = viewModel.tankList[tankNum].tankSize.toString()
//            }
//        })
        binding.calculateGallonPromptButton.setOnClickListener {
            binding.calculateGallonPromptButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalcGalFragment())
        }
        binding.calculateInfoButton.setOnClickListener {
//            if(binding.tankSizeCalculated.text == "") {
//                viewModel.setGal(0.0 + Integer.parseInt(binding.tankSizeInput.text.toString()))
//            }
//            else {
//                viewModel.setGal(binding.tankSizeCalculated.text.toString().toDouble())
//            }
        }
        return binding.root
    }
}