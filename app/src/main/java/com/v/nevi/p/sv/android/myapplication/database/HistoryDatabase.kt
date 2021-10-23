package com.v.nevi.p.sv.android.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.v.nevi.p.sv.android.myapplication.model.History

@Database(entities = [History::class],version = 1)
abstract class HistoryDatabase:RoomDatabase() {
    abstract fun getHistoryDao():HistoryDao
}