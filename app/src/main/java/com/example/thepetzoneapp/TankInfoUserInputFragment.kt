package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding
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
        binding.plantedSwitch.setChecked(viewModel.getPlanted(tankNumIndex))
        if(viewModel.getNumFish(tankNumIndex)>0) binding.numFishInput.setText(viewModel.getNumFish(tankNumIndex).toString())
        if(viewModel.getAverageFishLength(tankNumIndex)>0) binding.avgFishLengthInput.setText(viewModel.getAverageFishLength(tankNumIndex).toString())
        binding.calculateGallonPromptButton.setOnClickListener {
            binding.calculateGallonPromptButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalcGalFragment(tankNumIndex))
        }
        binding.calculateInfoButton.setOnClickListener {
            val tankSizeInputText = binding.tankSizeInput.text.toString()
            val numFishInputText = binding.numFishInput.text.toString()
            val avgFishLengthInputText = binding.avgFishLengthInput.text.toString()
            if((binding.tankSizeCalculated.text != "" || tankSizeInputText != "") && numFishInputText != "" && avgFishLengthInputText != "") {
                if(binding.tankSizeCalculated.text == "") {
                    viewModel.setGal(tankNumIndex,0.0 + Integer.parseInt(binding.tankSizeInput.text.toString()))
                }
                else {
                    viewModel.setGal(tankNumIndex, binding.tankSizeCalculated.text.toString().toDouble())
                }
                viewModel.setTankInfo(tankNumIndex,Integer.parseInt(binding.numFishInput.text.toString()),Integer.parseInt(binding.avgFishLengthInput.text.toString()),binding.plantedSwitch.isChecked)
                binding.calculateInfoButton.findNavController().navigate(TankInfoUserInputFragmentDirections.actionTankInfoUserInputFragmentToCalculatedWaterChangeFragment(tankNumIndex))
            }
            else Toast.makeText(context,R.string.warning,LENGTH_SHORT).show()
        }
        return binding.root
    }
}