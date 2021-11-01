package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.v.nevi.p.sv.android.myapplication.model.History
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class HistoryFragment : Fragment() {

    private val model: MainActivityViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private var deleteCallback: DeleteCallback? = null
    private var counterCallback: CounterCallback? = null
    private var isAddedFirstHistory: Boolean = false

    interface DeleteCallback {
        fun startDeleteDialog()
    }
    interface RecreateHistoryFragment{
        fun recreateHistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAddedFirstHistory = CounterPreferences.isAddedFirstHistory(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        deleteCallback = context as DeleteCallback
        counterCallback = context as CounterCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (isAddedFirstHistory) {
            inflater.inflate(R.layout.fragment_history, container, false)
        } else {
            inflater.inflate(R.layout.fragment_history_message, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isAddedFirstHistory) {
            recyclerView = view.findViewById(R.id.recycler_history)
            recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = HistoryAdapter(emptyList())
            recyclerView.adapter = adapter
            val itemAnimator = recyclerView.itemAnimator
            if (itemAnimator is DefaultItemAnimator) {
                itemAnimator.supportsChangeAnimations = false
            }
            val floatingButton: FloatingActionButton = view.findViewById(R.id.delete_button)
            model.liveDataHistoryList.observe(viewLifecycleOwner) {
                    adapter.list = it.reversed()
            }
            model.liveDataGetSumCount.observe(viewLifecycleOwner) {
                adapter.sumCount=it ?: 0
            }
            model.liveDataSumAttempts.observe(viewLifecycleOwner) {
                adapter.countAttempts = it?:0
            }
            floatingButton.setOnClickListener {
                deleteCallback?.startDeleteDialog()
            }
        } else {
            val button: Button = view.findViewById(R.id.button_not_have_any_history)
            button.setOnClickListener {
                counterCallback?.startCounter()
            }
        }
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateTextView: TextView = itemView.findViewById(R.id.date_text_view)
        private val countTextView: TextView = itemView.findViewById(R.id.count_text_view)
        private val countApproachesTextView: TextView =
            itemView.findViewById(R.id.count_approaches_text_view)

        fun bind(history: History) {
            val dateF: DateFormat =
                SimpleDateFormat("EEEE, d MMMM yyyy", Locale(Locale.ENGLISH.language))
            val date = dateF.parse(history.date)
            val locale = getSupportedLocale()
            val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", locale).format(date)
            val first = dateFormat.substring(0, 1).uppercase(Locale.getDefault())
            val last = dateFormat.substring(1)
            val resultDate = first + last
            dateTextView.text = resultDate
            countTextView.text = history.count.toString()
            val countOfApproaches =
                context?.resources?.getString(R.string.count_approaches) + " " + history.countOfApproaches
            countApproachesTextView.text = countOfApproaches
        }
    }


    private fun getSupportedLocale(): Locale {
        return when (Locale.getDefault()) {
            Locale.forLanguageTag("ru-RU") -> Locale.forLanguageTag("ru-RU")
            else -> Locale.ENGLISH
        }
    }

    inner class AllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pushUpsAll: TextView = itemView.findViewById(R.id.count_all_push_ups)
        private val attemptsAll: TextView = itemView.findViewById(R.id.count_all_attempts)

        fun bind(pushUps: Int, attempts: Int) {
            pushUpsAll.text = pushUps.toString()
            attemptsAll.text = attempts.toString()
        }
    }

    inner class HistoryAdapter(list: List<History>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var list: List<History> = list
            set(value) {
                //val oldSize=field.size
                field = value
                if(field.isEmpty())
                    notifyDataSetChanged()
                else
                    notifyItemRangeChanged(1,field.size)

            }
        var sumCount: Int = 0
            set(value) {
                field = value
                notifyItemChanged(0)
            }
        var countAttempts: Int = 0
            set(value) {
                field = value
                notifyItemChanged(0)
            }

        private val TYPE_HEADER = 0
        private val TYPE_ITEM = 1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == TYPE_HEADER) {
                val v: View = LayoutInflater.from(context)
                    .inflate(R.layout.item_all_history_list, parent, false)
                AllViewHolder(v)
            } else {
                val v: View =
                    LayoutInflater.from(context).inflate(R.layout.item_history_list, parent, false)
                HistoryViewHolder(v)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (position == 0) {
                holder as AllViewHolder
                holder.bind(sumCount, countAttempts)
            } else {
                holder as HistoryViewHolder
                holder.bind(list[position - 1])

            }
        }

        override fun getItemCount(): Int = list.size + 1
        override fun getItemViewType(position: Int): Int {
            return if (position == 0) {
                TYPE_HEADER
            } else {
                TYPE_ITEM
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        deleteCallback = null
        counterCallback = null
    }
}
