package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.thepetzoneapp.databinding.FragmentCalcGalBinding
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding
import com.example.thepetzoneapp.databinding.FragmentTankListBinding

class CalcGalFragment : Fragment() {
    private var _binding: FragmentCalcGalBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TankViewModel by activityViewModels ()
    var tankNumIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalcGalBinding.inflate(inflater,container,false)
        val args = CalcGalFragmentArgs.fromBundle((requireArguments()))
        tankNumIndex = args.tankNum
        binding.calculateGallonDataButton.setOnClickListener {
            var length = Integer.parseInt(binding.lengthInput.getText().toString())
            var width = Integer.parseInt(binding.widthInput.getText().toString())
            var depth = Integer.parseInt(binding.depthInput.getText().toString())
            var gallon = (length*width*depth)/231.0
            viewModel.setGal(tankNumIndex,gallon)
            binding.root.findNavController().navigateUp()
        }
        return binding.root
    }
}