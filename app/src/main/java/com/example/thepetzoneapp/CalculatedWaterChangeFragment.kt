package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.thepetzoneapp.databinding.FragmentCalculatedWaterChangeBinding
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding

class CalculatedWaterChangeFragment : Fragment() {
    private var _binding: FragmentCalculatedWaterChangeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TankViewModel by viewModels()
    var tankNumIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatedWaterChangeBinding.inflate(inflater,container,false)
        val args = TankInfoUserInputFragmentArgs.fromBundle((requireArguments()))
        tankNumIndex = args.tankNum
        var waterChangeSize = calculateWaterChangeInfo().toInt()
        binding.calculatedWaterChangeInfoText.text = getString(R.string.wtr_chng_info1) + " " + waterChangeSize.toString() + getString(R.string.wtr_chng_info2) + getString(R.string.week)
        return binding.root
    }

    fun calculateWaterChangeInfo(): Double {
        var c1 = calculateNitrateProduction()
        val vol1 = viewModel.getTankSize(tankNumIndex)
        var C = c1
        var p = 0.0
        while(C >= 1.0){
            p += 0.01
            C = ((c1/100)*(vol1-vol1*p))/(vol1*100)
        }
        return p
    }

    fun calculateNitrateProduction(): Double {
        val nf = viewModel.getNumFish(tankNumIndex)
        val afl = viewModel.getAverageFishLength(tankNumIndex)
        val vol1 = viewModel.getTankSize(tankNumIndex)
        var nitrateProductionPerWeek = (afl*nf*7)/vol1
        if(viewModel.getPlanted(tankNumIndex)) nitrateProductionPerWeek = nitrateProductionPerWeek/2
        return nitrateProductionPerWeek
    }
}