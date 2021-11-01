package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import java.lang.Exception

class AchieveRepository private constructor(context: Context) {
    val listAchieves:MutableList<Achieve> = mutableListOf()
    init {
        listAchieves.add(Achieve(100,context.getString(R.string.beginner)))
        listAchieves.add(Achieve(500,context.getString(R.string.novice)))
        listAchieves.add(Achieve(1000,context.getString(R.string.good_shaped)))
        listAchieves.add(Achieve(2000,context.getString(R.string.top_guy)))
        listAchieves.add(Achieve(3000,context.getString(R.string.sportsman)))
        listAchieves.add(Achieve(5000,context.getString(R.string.fighter)))
        listAchieves.add(Achieve(10000,context.getString(R.string.gladiator)))
        listAchieves.add(Achieve(15000,context.getString(R.string.superman)))
        listAchieves.add(Achieve(20000,context.getString(R.string.titan)))
        listAchieves.add(Achieve(30000,context.getString(R.string.god)))
    }
    companion object {
        private  var INSTANCE: AchieveRepository?=null
        fun initialize(context: Context){
            if(INSTANCE==null)
            INSTANCE=AchieveRepository(context)
        }
        fun getInstance():AchieveRepository{

            return INSTANCE?:throw Exception()
        }
    }
}