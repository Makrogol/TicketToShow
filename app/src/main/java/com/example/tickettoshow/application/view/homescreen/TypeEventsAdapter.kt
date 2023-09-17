package com.example.tickettoshow.application.view.homescreen


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.application.model.event.DataTypeEvents
import com.example.tickettoshow.databinding.ItemTypeEventRecyclerviewBinding


class TypeEventsAdapter : RecyclerView.Adapter<TypeEventsAdapter.TypeEventsViewHolder>() {

    private val _typeEvents = mutableListOf<DataTypeEvents>()
    val typeEvents: List<DataTypeEvents> = _typeEvents

    @SuppressLint("NotifyDataSetChanged")
    fun addTypeEvents(typeEvents: List<DataTypeEvents>) {
        _typeEvents.addAll(typeEvents)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeEventsViewHolder {
        val binding = ItemTypeEventRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TypeEventsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TypeEventsViewHolder, position: Int) {
        val typeEvent = _typeEvents[position]
        with(holder.binding) {
            concertsRecyclerview.adapter = typeEvent.adapter
            concertsTextview.text = typeEvent.eventsName
        }
    }


    override fun getItemCount() = _typeEvents.size


    class TypeEventsViewHolder(
        val binding: ItemTypeEventRecyclerviewBinding,
    ) : RecyclerView.ViewHolder(binding.root)

}