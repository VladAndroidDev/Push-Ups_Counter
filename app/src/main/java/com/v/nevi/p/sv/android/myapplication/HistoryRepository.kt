package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.v.nevi.p.sv.android.myapplication.database.HistoryDao
import com.v.nevi.p.sv.android.myapplication.database.HistoryDatabase
import com.v.nevi.p.sv.android.myapplication.model.History
import java.lang.Exception
import java.util.concurrent.Executors


private const val DATABASE_NAME = "history-database"
class HistoryRepository private constructor(context: Context) {

    private val executor = Executors.newSingleThreadExecutor()

    private val database:HistoryDatabase = Room.databaseBuilder(context.applicationContext,
        HistoryDatabase::class.java,
        DATABASE_NAME).build()

    private val dao:HistoryDao=database.getHistoryDao()

    fun insertHistory(history: History){
        executor.execute {
            dao.addHistory(history)
        }
    }
    fun deleteAllHistory(){
        executor.execute { dao.deleteAllHistory() }
    }

    fun getSumAttempts()=dao.getSumAttemptsLiveData()
    fun getSumCount()=dao.getSumCountLiveData()
    fun getAllHistories() = dao.getAllHistories()

    fun updateHistory(history: History){
        executor.execute {
            dao.updateHistory(history)
        }
    }

    fun getHistoryByIdLiveData(date:String)=dao.getHistoryByDate(date)

    companion object{
        private var INSTANCE:HistoryRepository?=null
        fun initialize(context: Context){
            if(INSTANCE==null){
                INSTANCE = HistoryRepository(context)
            }
        }

        fun getInstance():HistoryRepository{
            return INSTANCE?:throw Exception()
        }
    }

}