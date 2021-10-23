package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.v.nevi.p.sv.android.myapplication.model.History


class HistoryFragment : Fragment() {

    private val model:MainActivityViewModel by activityViewModels()
    private lateinit var recyclerView:RecyclerView
    private var deleteCallback:DeleteCallback?=null
    interface DeleteCallback{
        fun startDeleteDialog()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        deleteCallback = context as DeleteCallback
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_history)
        recyclerView.layoutManager=LinearLayoutManager(context)
        model.liveDataHistoryList.observe(viewLifecycleOwner){
            recyclerView.adapter=HistoryAdapter(it)
        }
        val button:FloatingActionButton=view.findViewById(R.id.delete_button)
        button.setOnClickListener{
            deleteCallback?.startDeleteDialog()
        }
    }
   inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val dateTextView:TextView = itemView.findViewById(R.id.date_text_view)
        private val countTextView:TextView = itemView.findViewById(R.id.count_text_view)
        private val countApproachesTextView:TextView = itemView.findViewById(R.id.count_approaches_text_view)

        fun bind(history: History){
            //history.date[0].uppercaseChar()
            dateTextView.text = history.date
            countTextView.text=history.count.toString()
            val countOfApproaches = context?.resources?.getString(R.string.count_approaches)+" "+history.countOfApproaches
            countApproachesTextView.text = countOfApproaches
        }
    }
    inner class HistoryAdapter(private val list: List<History>): RecyclerView.Adapter<HistoryViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
            val v:View=LayoutInflater.from(context).inflate(R.layout.item_history_list,parent,false)
            return HistoryViewHolder(v)
        }

        override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int = list.size
    }

    override fun onDetach() {
        super.onDetach()
        deleteCallback=null
    }
}