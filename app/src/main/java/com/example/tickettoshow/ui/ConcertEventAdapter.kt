package com.example.tickettoshow.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.databinding.ItemConcertsRecyclerviewBinding
import com.example.tickettoshow.model.DataEvent

class ConcertEventAdapter(
    private val routeToDescriptionEventFragmentCallback: (event: DataEvent) -> Unit,
) : RecyclerView.Adapter<ConcertEventAdapter.ConcertEventViewHolder>() {

    private val _shows = mutableListOf<DataEvent>()
    val shows: List<DataEvent> = _shows

    @SuppressLint("NotifyDataSetChanged")
    fun addShows(shows: List<DataEvent>) {
        _shows.addAll(shows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConcertEventViewHolder(
        ItemConcertsRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        routeToDescriptionEventFragmentCallback
    )


    override fun onBindViewHolder(holder: ConcertEventViewHolder, position: Int) =
        holder.bind(_shows[position])

    override fun getItemCount() = _shows.size


    class ConcertEventViewHolder(
        private val binding: ItemConcertsRecyclerviewBinding,
        private val routeToDescriptionEventFragmentCallback: (event: DataEvent) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: DataEvent) = with(binding) {
            itemShowNameTextview.text = event.name
            itemShowAddressTextview.text = event.address
            itemShowDateTextview.text = event.date
            itemShowTimeTextview.text = event.time
            itemShowPosterImageview.setImageResource(event.posterId)

            itemShowPosterImageview.setOnClickListener {
                routeToDescriptionEventFragmentCallback(
                    event
                )
            }
        }
    }

}