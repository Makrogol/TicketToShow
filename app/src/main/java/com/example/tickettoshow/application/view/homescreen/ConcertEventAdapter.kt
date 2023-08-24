package com.example.tickettoshow.application.view.homescreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.databinding.ItemConcertsRecyclerviewBinding


class ConcertEventAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<ConcertEventAdapter.ConcertEventViewHolder>(), View.OnClickListener {

    private val _events = mutableListOf<EventListItem>()
    val events: List<EventListItem> = _events

    @SuppressLint("NotifyDataSetChanged")
    fun addShows(shows: List<EventListItem>) {
        _events.addAll(shows)
        notifyDataSetChanged()
    }


    override fun onClick(v: View) {
        val event = v.tag as EventListItem

        listener.onEventClick(event.event)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertEventViewHolder {
        val binding = ItemConcertsRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ConcertEventViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ConcertEventViewHolder, position: Int) {
        holder.itemView.tag = _events[position]
        holder.binding.root.setOnClickListener(this)

        val eventItem = _events[position]
        val event = eventItem.event
        with(holder.binding) {

            if (eventItem.isInProgress) {
                itemShowPosterImageview.visibility = View.GONE
                root.setOnClickListener(null)
            } else {
                itemShowPosterImageview.visibility = View.VISIBLE
                root.setOnClickListener {listener.onEventClick(event)}
            }

            itemShowNameTextview.text = event.name
            itemShowAddressTextview.text = event.address
            itemShowDateTextview.text = event.date
            itemShowTimeTextview.text = event.time
            itemShowPosterImageview.setImageResource(event.posterId)
        }
    }


    override fun getItemCount() = _events.size


    class ConcertEventViewHolder(
        val binding: ItemConcertsRecyclerviewBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onEventClick(event: DataEvent)
    }

}