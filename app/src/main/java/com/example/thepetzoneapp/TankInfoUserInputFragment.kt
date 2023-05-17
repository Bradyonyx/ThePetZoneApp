package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding
import com.example.thepetzoneapp.databinding.FragmentTankListBinding
import java.text.DecimalFormat

class TankInfoUserInputFragment : Fragment() {
    private var _binding: FragmentTankInfoUserInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TankViewModel by activityViewModels ()
    var tankNumIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTankInfoUserInputBinding.inflate(inflater,container,false)
        val args = TankInfoUserInputFragmentArgs.fromBundle((requireArguments()))
        tankNumIndex = args.tankNum
        if(viewModel.getTankSize(tankNumIndex) > 0.0){
            val dec = DecimalFormat("#,###.0")
            binding.tankSizeCalculated.text = dec.format(viewModel.getTankSize(tankNumIndex)).toString()
            binding.tankSizeInput.setVisibility(INVISIBLE)
        }
        binding.calculateGallonPromptButton.setOnClickListener {
            binding.calculateGallonPromptButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalcGalFragment(tankNumIndex))
        }
        binding.calculateInfoButton.setOnClickListener {
            if(binding.tankSizeCalculated.text == "") {
                viewModel.setGal(tankNumIndex,0.0 + Integer.parseInt(binding.tankSizeInput.text.toString()))
            }
            else {
                viewModel.setGal(tankNumIndex, binding.tankSizeCalculated.text.toString().toDouble())
            }
            viewModel.setTankInfo(tankNumIndex,Integer.parseInt(binding.numFishInput.text.toString()),Integer.parseInt(binding.avgFishLengthInput.text.toString()),binding.plantedSwitch.isActivated)
            binding.calculateInfoButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalculatedWaterChangeFragment(tankNumIndex))
        }
        return binding.root
    }
}