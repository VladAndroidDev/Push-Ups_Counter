package com.v.nevi.p.sv.android.myapplication.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.v.nevi.p.sv.android.myapplication.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_table")
    fun getAllHistories():LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHistory(history: History)

    @Update
    fun updateHistory(history: History)

    @Query("SELECT * FROM history_table WHERE date=:date")
    fun getHistoryByDate(date:String):LiveData<History>

    @Query("DELETE FROM history_table")
    fun deleteAllHistory()
}