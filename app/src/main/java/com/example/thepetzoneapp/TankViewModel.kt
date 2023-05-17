package com.example.thepetzoneapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.reflect.Array.get

class TankViewModel : ViewModel() {
    private val _observableTankList = MutableLiveData<List<Tank>>()
    val observableTankList: LiveData<List<Tank>>
        get() = _observableTankList
    var tankNumber = 0
    val tankList = mutableListOf<Tank>()

    fun addTank() {
        tankNumber++
        tankList.add(Tank(tankNumber,"Tank "+tankNumber,0.0,0,0))
        var showTankNum = tankList.find {it.tankName == "Tank 1"}?.tankNum
        updateObservableTankList()
    }

    fun updateObservableTankList() {
        _observableTankList.value = tankList
    }

    fun setGal(tankIndex: Int, gal: Double) {
        tankList.find{it.tankNum == tankIndex}?.tankSize = gal
        var checkGal = tankList.find{it.tankNum == tankIndex}?.tankSize
    }

    fun setTankInfo(tankIndex: Int, numFish: Int, avgFishLength: Int, planted: Boolean) {
        tankList.find{it.tankNum == tankIndex}?.numFish = numFish
        tankList.find{it.tankNum == tankIndex}?.avgFishLength = avgFishLength
        tankList.find{it.tankNum == tankIndex}?.planted = planted
    }

    fun getTankSize(tankIndex: Int): Double {
        var tankListSize = tankNumber
        if(tankNumber > 0) return tankList.find{it.tankNum == tankIndex}!!.tankSize
        return 0.0
    }

    fun getNumFish(tankIndex: Int): Int {
        return tankList.find{it.tankNum == tankIndex}!!.numFish
    }

    fun getAverageFishLength(tankIndex: Int): Int {
        return tankList.find{it.tankNum == tankIndex}!!.avgFishLength
    }

    fun getPlanted(tankIndex: Int): Boolean {
        return tankList.find{it.tankNum == tankIndex}!!.planted
    }
}