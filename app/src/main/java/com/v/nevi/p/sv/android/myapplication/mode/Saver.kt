package com.v.nevi.p.sv.android.myapplication.mode

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.v.nevi.p.sv.android.myapplication.HistoryRepository
import com.v.nevi.p.sv.android.myapplication.counter.Counter
import com.v.nevi.p.sv.android.myapplication.model.History
import java.text.SimpleDateFormat
import java.util.*

class Saver(context: Context) {

    private val repository = HistoryRepository.getInstance()
    private lateinit var currentDate: String
    private var currentHistory: History? = null

    init {
        initHistory(context)
    }

    private fun initHistory(context: Context) {
        context as LifecycleOwner
        val date = Calendar.getInstance().time
        currentDate =
            SimpleDateFormat("EEEE, d MMMM yyyy", Locale(Locale.ENGLISH.language)).format(date)
        repository.getHistoryByIdLiveData(currentDate).observe(context) {
            currentHistory = it
        }
    }

    fun save(counter: Counter) {
        val count = counter.countPushUps
        var countAll = counter.countAllPushUps
        var countAttempts = counter.countAttempts

        if (counter.isNotEmpty()) {
            if (countAttempts == 0 || count != 0) {
                countAttempts++
                countAll+=count
            }
            if (currentHistory != null) {
                currentHistory!!.count += countAll
                currentHistory!!.countOfApproaches += countAttempts
                repository.updateHistory(currentHistory!!)
            } else {
                currentHistory = History(currentDate, countAll, countAttempts)
                repository.insertHistory(currentHistory!!)
            }
        }
    }
}