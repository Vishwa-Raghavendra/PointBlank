package com.dscepointblank.pointblank.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.models.Object
import kotlinx.android.synthetic.main.row_coding_event.view.*

class EventsAdapter() : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>()  {

    var eventlist = ArrayList<Object>()

    private val differCallBack = object : DiffUtil.ItemCallback<Object>()
    {
        override fun areItemsTheSame(oldItem: Object, newItem: Object): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Object, newItem: Object): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)

    inner class EventsViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val eventName : TextView = itemView.tv_eventName
    }


     fun  setList(list2 :ArrayList<Object>)
     {
         eventlist = list2
         notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder  =
        EventsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_coding_event,parent,false))

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.eventName.text = item.event
    }
}