package com.example.thepetzoneapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thepetzoneapp.databinding.ListItemLayoutBinding

class TankAdapter (val tankList: List<Tank>) : RecyclerView.Adapter<TankViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TankViewHolder {
        val binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TankViewHolder, position: Int) {
        holder.bindTank(tankList[position])
    }

    override fun getItemCount(): Int {
        return tankList.size
    }
}