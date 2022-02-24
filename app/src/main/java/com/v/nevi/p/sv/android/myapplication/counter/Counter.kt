package com.v.nevi.p.sv.android.myapplication.counter

open class Counter {

    var countPushUps=0
    var countAllPushUps=0
    var countAttempts=0

    fun resetCounter(){
        if (countPushUps != 0) {
            countAllPushUps+=countPushUps
            countAttempts++
        }
        countPushUps = 0
    }
    fun incrementPushUps(){
        countPushUps++
    }
    fun addPushUps(count:Int){
        countPushUps+=count
    }
    fun isNotEmpty():Boolean{
        return countAllPushUps != 0 || countPushUps!=0
    }
}