package com.dscepointblank.pointblank.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.models.Object
import com.dscepointblank.pointblank.utilityClasses.Constants
import kotlinx.android.synthetic.main.trial2.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventsAdapter() : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    var eventlist = ArrayList<Object>()

    private val differCallBack = object : DiffUtil.ItemCallback<Object>() {
        override fun areItemsTheSame(oldItem: Object, newItem: Object): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Object, newItem: Object): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.tv_eventName
        val startDate: TextView = itemView.tv_eventStartDate
        val endDate: TextView = itemView.tv_eventEndDate
        val to :TextView = itemView.tv_to

        //        val duration :MaterialButton  = itemView.btn_duration
        val eventWebsite: TextView = itemView.tv_eventWebsite

        //        val websiteColor :View = itemView.v_websiteColor
//        val view2 :View = itemView.view2
        val sideBar: View = itemView.sideBar
    }


    fun setList(list2: ArrayList<Object>) {
        eventlist = list2
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.trial2, parent, false)
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.eventName.text = item.event

        val fromSDF = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US)

        val toSDF = SimpleDateFormat("dd MMM '@' hh:mm aa", Locale.US)
//
//
//
//        holder.eventName.text = item.event
//
//        if(item.resource.id==1||item.resource.id==81)
//        {
//             holder.eventName.setTextColor(ContextCompat.getColor(holder.websiteColor.context,R.color.deepBlack))
//        }
//
        holder.startDate.text = toSDF.format(fromSDF.parse(item.start)!!)
        holder.endDate.text = toSDF.format(fromSDF.parse(item.end)!!)
//        holder.duration.text = "${item.duration/3600} Hours"
        holder.eventWebsite.text = Constants.CONTEST_WEBSITE_NAME[item.resource.id]
//
        holder.eventWebsite.setTextColor(
            ContextCompat.getColor(
                holder.eventWebsite.context,
                Constants.CONTEST_WEBSITE_COLOR[item.resource.id]!!
            )
        )
        holder.to.setTextColor(
            ContextCompat.getColor(
                holder.eventWebsite.context,
                Constants.CONTEST_WEBSITE_COLOR[item.resource.id]!!
            )
        )
//        holder.view2.setBackgroundColor(ContextCompat.getColor(holder.websiteColor.context,Constants.CONTEST_WEBSITE_COLOR[item.resource.id]!!))
//

        holder.sideBar.setBackgroundColor(
            ContextCompat.getColor(
                holder.sideBar.context,
                Constants.CONTEST_WEBSITE_COLOR[item.resource.id]!!
            )
        )
        //holder.sideBar.cardBackgroundColor()
    }
}