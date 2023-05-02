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
        tankList.add(Tank("Tank "+tankNum,0.0,0, 0))
        updateTankList()
    }

    fun updateTankList() {
        _observableTankList.value = tankList
    }

    fun setGal(gal: Double) {
        tankList[tankNum-1].tankSize = gal
    }
}