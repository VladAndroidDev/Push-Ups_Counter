package com.v.nevi.p.sv.android.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AchieveFragment: Fragment() {
    private lateinit var recyclerView:RecyclerView
    private val achieveRepository=AchieveRepository.getInstance()
    private val model:MainActivityViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achieve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView=view.findViewById(R.id.achieve_recycler)
        recyclerView.layoutManager=LinearLayoutManager(context)
        model.liveDataGetSumCount.observe(viewLifecycleOwner) {
            if (it == null) {
                recyclerView.adapter = AchieveAdapter(achieveRepository.listAchieves)
            } else {
                recyclerView.adapter = AchieveAdapter(achieveRepository.listAchieves, it)
            }
        }
    }
    inner class AchieveViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val achieveCount:TextView=itemView.findViewById(R.id.achieve_count)
        private val achieveName:TextView=itemView.findViewById(R.id.achieve_name)
        private val view:View=itemView.findViewById(R.id.view)
        private val achieveLayout:ConstraintLayout=itemView.findViewById(R.id.item_achieve_list)
        private val firstWord:String= context?.getString(R.string.make_word)!!
        private val lastWord:String= context?.getString(R.string.push_ups)!!
        fun bind(count:Int,name:String,isRed: Boolean){
            achieveName.text=name
            val countStr= "$firstWord $count $lastWord"
            achieveCount.text=countStr
            if(isRed){

                achieveLayout.background= context?.resources?.let { val drawable =
                    ResourcesCompat.getDrawable(
                        it,
                        R.drawable.background_achieve_item_red,
                        context!!.theme
                    )
                    drawable
                }
                //view.setBackgroundColor(resources.getColor(R.color.purple_500))
                context?.resources?.getColor(R.color.white)?.let { achieveCount.setTextColor(it) }
                context?.resources?.getColor(R.color.white)?.let { achieveName.setTextColor(it) }
            }
        }
    }
    inner class AchieveAdapter(private val list:List<Achieve>, private val sumCount:Int=0):RecyclerView.Adapter<AchieveViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchieveViewHolder {
            val v:View=LayoutInflater.from(context).inflate(R.layout.item_achieve_list,parent,false)
            return AchieveViewHolder(v)
        }

        override fun onBindViewHolder(holder: AchieveViewHolder, position: Int) {
            val achieve=list[position]
            val count=achieve.count
            val name=achieve.name
            val isRed=sumCount>=count
            holder.bind(count,name,isRed)
        }

        override fun getItemCount(): Int = list.size

    }
}