package com.v.nevi.p.sv.android.myapplication.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(@PrimaryKey val date:String, var count:Int, var countOfApproaches:Int)