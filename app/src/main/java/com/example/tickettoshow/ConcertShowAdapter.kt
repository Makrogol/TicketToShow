package com.example.tickettoshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.databinding.ItemConcertsRecyclerviewBinding

class ConcertShowAdapter() : RecyclerView.Adapter<ConcertShowAdapter.ConcertShowViewHolder>() {

    private val _shows = mutableListOf<DataShow>()
    val shows: List<DataShow> = _shows

    @SuppressLint("NotifyDataSetChanged")
    fun addShows(shows: List<DataShow>) {
        _shows.addAll(shows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConcertShowViewHolder(
        ItemConcertsRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ConcertShowViewHolder, position: Int) =
        holder.bind(_shows[position])

    override fun getItemCount() = _shows.size


    class ConcertShowViewHolder(
        private val binding: ItemConcertsRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(show: DataShow) = with(binding) {
            showNameTextview.text = show.name
            showAddressTextview.text = show.address
            showDateTextview.text = show.date
            showTimeTextview.text = show.time
            showPosterImageview.setImageDrawable(show.poster)
        }
    }

}