package com.example.tickettoshow.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.databinding.ItemDateAndPayRecyclerviewBinding
import com.example.tickettoshow.model.DataDateAndPay

class DateAndPayAdapter() : RecyclerView.Adapter<DateAndPayAdapter.DateAndPayViewHolder>() {

    private val _dates = mutableListOf<DataDateAndPay>()
    val dates: List<DataDateAndPay> = _dates

    @SuppressLint("NotifyDataSetChanged")
    fun addShows(dates: List<DataDateAndPay>) {
        _dates.addAll(dates)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DateAndPayViewHolder(
        ItemDateAndPayRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: DateAndPayViewHolder, position: Int) =
        holder.bind(_dates[position])

    override fun getItemCount() = _dates.size


    class DateAndPayViewHolder(
        private val binding: ItemDateAndPayRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: DataDateAndPay) = with(binding) {
            itemPayButton.text = date.minPrice
            itemDateTextview.text = date.date
            itemTimeTextview.text = date.time
        }
    }

}