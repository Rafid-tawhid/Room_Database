package com.example.pickers2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pickers2.databinding.ScheduleRowBinding
class ScheduleAdapter() :ListAdapter<BusScedule,ScheduleAdapter.ScheduleViewHoldder>(MovieDiffUtil()) {

    class ScheduleViewHoldder(val binding:ScheduleRowBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(schedule:BusScedule){
            binding.schedule=schedule
        }

    }



    class MovieDiffUtil:DiffUtil.ItemCallback<BusScedule>(){
        override fun areItemsTheSame(oldItem: BusScedule, newItem: BusScedule): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: BusScedule, newItem: BusScedule): Boolean {

            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHoldder {
        val binding=ScheduleRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return ScheduleViewHoldder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHoldder, position: Int) {
        val schedule=getItem(position)
//        if(schedule.isFav){
//            holder.binding.imageView.setImageResource(R.drawable.non_fav)
//        }
//        else{
//            holder.binding.imageView.setImageResource(R.drawable.fav)
//        }
        holder.bind(schedule)

        holder.binding.imageView.setOnClickListener {
            schedule.isFav=!schedule.isFav
            holder.bind(schedule)
//
//            if(schedule.isFav){
//                holder.binding.imageView.setImageResource(R.drawable.non_fav)
//            }
//            else{
//                holder.binding.imageView.setImageResource(R.drawable.fav)
//            }

        }
        holder.itemView.setOnClickListener {
//            callBack(movie)
        }

//        holder.binding.imageView2.setOnClickListener {
//            callBack(movie)
//        }

    }
}