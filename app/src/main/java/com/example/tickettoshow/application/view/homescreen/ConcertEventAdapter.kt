package com.example.tickettoshow.application.view.homescreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.databinding.ItemEventsRecyclerviewBinding


class ConcertEventAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<ConcertEventAdapter.ConcertEventViewHolder>(), View.OnClickListener {

    private val _events = mutableListOf<DataEvent>()
    val events: List<DataEvent> = _events

    @SuppressLint("NotifyDataSetChanged")
    fun addShows(shows: List<DataEvent>) {
        _events.addAll(shows)
        notifyDataSetChanged()
    }


    override fun onClick(v: View) {
        val event = v.tag as DataEvent

        listener.onEventClick(event)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertEventViewHolder {
        val binding = ItemEventsRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ConcertEventViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ConcertEventViewHolder, position: Int) {
        holder.itemView.tag = _events[position]
        holder.binding.root.setOnClickListener(this)

        val event = _events[position]
        with(holder.binding) {
            itemShowPosterImageview.visibility = View.VISIBLE
            itemShowNameTextview.text = event.name
            itemShowAddressTextview.text = event.address
            itemShowDateAndTimeTextview.text = "${event.date}\n${event.time}"
            itemShowPosterImageview.setImageResource(event.posterId)
        }
    }


    override fun getItemCount() = _events.size


    class ConcertEventViewHolder(
        val binding: ItemEventsRecyclerviewBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onEventClick(event: DataEvent)

        fun onFavoriteClick(event: DataEvent)
    }

}