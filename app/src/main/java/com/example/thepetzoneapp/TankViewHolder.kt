package com.example.thepetzoneapp

import android.view.View.INVISIBLE
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.thepetzoneapp.databinding.ListItemLayoutBinding

class TankViewHolder (val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var currentTank: Tank

    init {
        binding.root.setOnClickListener {view ->
            binding.root.findNavController().navigate(TankListFragmentDirections.actionTankListFragmentToTankInfoUserInputFragment(currentTank.tankNum))
        }
    }

    fun bindTank(tank: Tank){
        currentTank = tank
        binding.tankName.text = currentTank.tankName
        binding.tankSize.text = currentTank.tankSize.toString() + " Gallons"
        binding.numFish.text = currentTank.numFish.toString() + " Fish"
        if(currentTank.planted) binding.planted.setVisibility(INVISIBLE)
    }
}