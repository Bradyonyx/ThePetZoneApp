package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
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
    var tankNumIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTankInfoUserInputBinding.inflate(inflater,container,false)
        val args = TankInfoUserInputFragmentArgs.fromBundle((requireArguments()))
        tankNumIndex = args.tankNum - 1
        if(viewModel.getTankSize(tankNumIndex) > 0.0){
            binding.tankSizeCalculated.text = viewModel.getTankSize(tankNumIndex).toString()
            binding.tankSizeInput.setVisibility(INVISIBLE)
        }
        binding.calculateGallonPromptButton.setOnClickListener {
            binding.calculateGallonPromptButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalcGalFragment(tankNumIndex))
        }
        binding.calculateInfoButton.setOnClickListener {
            if(binding.tankSizeCalculated.text == "") {
                viewModel.setGal(tankNumIndex+1,0.0 + Integer.parseInt(binding.tankSizeInput.text.toString()))
            }
            else {
                viewModel.setGal(tankNumIndex+1, binding.tankSizeCalculated.text.toString().toDouble())
            }
            viewModel.setTankInfo(tankNumIndex+1,Integer.parseInt(binding.numFishInput.text.toString()),Integer.parseInt(binding.avgFishLengthInput.text.toString()),binding.plantedSwitch.isActivated)
            binding.calculateInfoButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalculatedWaterChangeFragment(tankNumIndex))
        }
        return binding.root
    }
}