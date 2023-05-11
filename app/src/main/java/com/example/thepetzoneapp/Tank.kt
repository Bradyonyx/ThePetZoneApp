package com.example.thepetzoneapp

data class Tank (val tankNum: Int, val tankName: String, var tankSize: Double,
                 var numFish: Int, var avgFishLength: Int, var planted: Boolean = false,
                 var waterChangePercent: String = "") {
}