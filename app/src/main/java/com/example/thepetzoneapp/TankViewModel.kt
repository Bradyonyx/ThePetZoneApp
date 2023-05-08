package com.example.thepetzoneapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.reflect.Array.get

class TankViewModel : ViewModel() {
    private val _observableTankList = MutableLiveData<List<Tank>>()
    val observableTankList : LiveData<List<Tank>>
            get() = _observableTankList
    var tankNum = 0
    val tankList = mutableListOf<Tank>()

    fun addTank() {
        tankNum++
        tankList.add(Tank("Tank "+tankNum,0.0,0,0))
        updateTankList()
    }

    fun updateTankList() {
        _observableTankList.value = tankList
    }

    fun setGal(gal: Double) {
        tankList.find{it.tankName == "Tank "+tankNum}?.tankSize = gal
        updateTankList()
    }

    fun setTankInfo(numFish: Int, avgFishLength: Int, planted: Boolean) {
        tankList.find{it.tankName == "Tank "+tankNum}?.numFish = numFish
        tankList.find{it.tankName == "Tank "+tankNum}?.avgFishLength = avgFishLength
        tankList.find{it.tankName == "Tank "+tankNum}?.planted = planted
        updateTankList()
    }

    fun getTankSize(tankIndex: Int): Double {
        val listSize = _observableTankList.value?.size ?: 0
        if(listSize > 0) return _observableTankList.value?.get(tankIndex)!!.tankSize
        return 0.0
    }
}