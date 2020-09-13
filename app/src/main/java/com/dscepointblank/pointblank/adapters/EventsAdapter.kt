package com.dscepointblank.pointblank.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.models.FormattedEvents
import kotlinx.android.synthetic.main.trial2.view.*


class EventsAdapter(private val eventsAdapterListener: EventsAdapterListener) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    interface EventsAdapterListener {
        fun onEventShared(formattedEvents: FormattedEvents)
    }

    private val differCallBack = object : DiffUtil.ItemCallback<FormattedEvents>() {
        override fun areItemsTheSame(oldItem: FormattedEvents, newItem: FormattedEvents) =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: FormattedEvents, newItem: FormattedEvents) =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.tv_eventName
        val startDate: TextView = itemView.tv_eventStartDate
        val endDate: TextView = itemView.tv_eventEndDate
        val to: TextView = itemView.tv_to
        val eventWebsite: TextView = itemView.tv_eventWebsite
        val sideBar: View = itemView.sideBar
        val view = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.trial2, parent, false)
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.view.setOnLongClickListener {
            createDialog(it.context, item)
        }

        holder.eventName.text = item.eventName
        holder.startDate.text = item.startDate
        holder.endDate.text = item.endDate
        holder.eventWebsite.text = item.eventHost

        holder.eventWebsite.setTextColor(
            ContextCompat.getColor(
                holder.eventWebsite.context,
                item.eventColor
            )
        )
        holder.to.setTextColor(
            ContextCompat.getColor(
                holder.eventWebsite.context,
                item.eventColor
            )
        )

        holder.sideBar.setBackgroundColor(
            ContextCompat.getColor(
                holder.sideBar.context,
                item.eventColor
            )
        )
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    private fun createDialog(context: Context, formattedEvents: FormattedEvents): Boolean {
        val items = arrayOf<CharSequence>("Share Contest Details")

        val builder = AlertDialog.Builder(context)

        builder.setTitle("Select The Action")
        builder.setItems(
            items
        ) { dialog, item ->
            when (item) {
                0 -> eventsAdapterListener.onEventShared(formattedEvents)
            }
        }
        builder.show()
        return true
    }
}