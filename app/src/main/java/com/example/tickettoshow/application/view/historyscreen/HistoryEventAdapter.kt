package com.example.tickettoshow.application.view.historyscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.R
import com.example.tickettoshow.application.model.event.HistoryEvent
import com.example.tickettoshow.databinding.ItemHistoryRecyclerviewBinding

class HistoryEventAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<HistoryEventAdapter.HistoryEventViewHolder>(), View.OnClickListener {


    private val _events = mutableListOf<HistoryEvent>()
    val events: List<HistoryEvent> = _events

    @SuppressLint("NotifyDataSetChanged")
    fun addShows(shows: List<HistoryEvent>) {
        _events.addAll(shows)
        notifyDataSetChanged()
    }


    override fun onClick(v: View) {
        val event = v.tag as HistoryEvent

        listener.onEventClick(event)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryEventViewHolder {
        val binding = ItemHistoryRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HistoryEventViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryEventViewHolder, position: Int) {
        holder.itemView.tag = _events[position]
        holder.binding.root.setOnClickListener(this)
        val context = holder.itemView.context

        val event = _events[position]
        with(holder.binding) {
            itemShowNameTextview.text = event.name
            itemShowAddressTextview.text = event.address
            itemDateTextview.text = event.date
            itemTimeTextview.text = event.time
            itemDateBoughtTextview.text =
                context.getString(R.string.date_bought_text, event.dateBought)
            itemPlaceTextview.text = context.getString(R.string.place_text, event.place)
            itemPriceTextview.text = context.getString(R.string.price_text, event.price)
            // Потом надо изменять в зависимости от текущего дня
            itemVisitedTextview.text =
                if (event.visited) context.getString(R.string.visited_event_text)
                else context.getString(R.string.not_visited_event_text)
        }
    }


    override fun getItemCount() = _events.size


    class HistoryEventViewHolder(
        val binding: ItemHistoryRecyclerviewBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onEventClick(event: HistoryEvent)
    }
}