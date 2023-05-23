package com.example.thepetzoneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.thepetzoneapp.databinding.FragmentCalculatedWaterChangeBinding
import com.example.thepetzoneapp.databinding.FragmentTankInfoUserInputBinding
import java.text.DecimalFormat

class CalculatedWaterChangeFragment : Fragment() {
    private var _binding: FragmentCalculatedWaterChangeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TankViewModel by activityViewModels ()
    var tankNumIndex = 0
    var weekly = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatedWaterChangeBinding.inflate(inflater,container,false)
        val args = TankInfoUserInputFragmentArgs.fromBundle((requireArguments()))
        tankNumIndex = args.tankNum
        val dec = DecimalFormat("#,###.0")
        val waterChangeSizePercent = calculateWaterChangeInfo()
        val waterChangeSizeGal = dec.format(viewModel.getTankSize(tankNumIndex) * waterChangeSizePercent)
        var rate = getString(R.string.week)
        if(!weekly) rate = getString(R.string.month)
        binding.calculatedWaterChangeInfoText.text = getString(R.string.wtr_chng_info1) + " " + dec.format(waterChangeSizePercent*100) + "% (" + waterChangeSizeGal + " gal) " + getString(R.string.wtr_chng_info2) + " " + rate
        return binding.root
    }

    fun calculateWaterChangeInfo(): Double {
        var c1 = calculateNitrateProduction()
        val vol1 = viewModel.getTankSize(tankNumIndex)
        var C = c1
        var p = 0.0
        while(C >= 1.0){
            p += 0.01
            C = (c1*(vol1-vol1*p))/vol1
        }
        return p
    }

    fun calculateNitrateProduction(): Double {
        val nf = viewModel.getNumFish(tankNumIndex)
        val afl = viewModel.getAverageFishLength(tankNumIndex)
        val vol1 = viewModel.getTankSize(tankNumIndex)
        var nitrateProductionPerWeek = (afl*0.4*nf*7)/ vol1
        if(nitrateProductionPerWeek<1.0){
            weekly = false
            nitrateProductionPerWeek = (afl*0.4*nf*30)/ vol1
        }
        if(viewModel.getPlanted(tankNumIndex)) nitrateProductionPerWeek = nitrateProductionPerWeek/2
        return nitrateProductionPerWeek
    }
}