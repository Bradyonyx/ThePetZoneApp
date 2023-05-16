package com.example.thepetzoneapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.reflect.Array.get

class TankViewModel : ViewModel() {
    private val _observableTankList = MutableLiveData<List<Tank>>()
    val observableTankList: LiveData<List<Tank>>
        get() = _observableTankList
    var tankNum = 0
    val tankList = mutableListOf<Tank>()

    fun addTank() {
        tankNum++
        tankList.add(Tank(tankNum,"Tank "+tankNum,0.0,0,0))
        updateObservableTankList()
    }

    fun updateObservableTankList() {
        _observableTankList.value = tankList
    }

    fun setGal(tankIndex: Int, gal: Double) {
        tankList.find{it.tankName == "Tank "+tankIndex}?.tankSize = gal
    }

    fun setTankInfo(tankIndex: Int, numFish: Int, avgFishLength: Int, planted: Boolean) {
        tankList.find{it.tankName == "Tank "+tankIndex}?.numFish = numFish
        tankList.find{it.tankName == "Tank "+tankIndex}?.avgFishLength = avgFishLength
        tankList.find{it.tankName == "Tank "+tankIndex}?.planted = planted
    }

    fun getTankSize(tankIndex: Int): Double {
        if(tankList.size > 0) return tankList.find{it.tankName == "Tank "+tankIndex}!!.tankSize
        return 0.0
    }

    fun getNumFish(tankIndex: Int): Int {
        if(tankList.size > 0) return tankList.find{it.tankName == "Tank "+tankIndex}!!.numFish
        return 0
    }

    fun getAverageFishLength(tankIndex: Int): Int {
        if(tankList.size > 0) return tankList.find{it.tankName == "Tank "+tankIndex}!!.avgFishLength
        return 0
    }

    fun getPlanted(tankIndex: Int): Boolean {
        if(tankList.size > 0) return tankList.find{it.tankName == "Tank "+tankIndex}!!.planted
        return false
    }
}