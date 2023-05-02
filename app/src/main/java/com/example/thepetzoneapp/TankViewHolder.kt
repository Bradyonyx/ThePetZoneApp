package com.example.thepetzoneapp

import android.view.View.INVISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.example.thepetzoneapp.databinding.ListItemLayoutBinding

class TankViewHolder (val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var currentTank: Tank

    fun bindTank(tank: Tank){
        currentTank = tank
        binding.tankName.text = currentTank.tankName
        binding.tankSize.text = currentTank.size.toString() + " Gallons"
        binding.numFish.text = currentTank.numFish.toString() + " Fish"
        if(currentTank.planted) binding.planted.setVisibility(INVISIBLE)
    }
}