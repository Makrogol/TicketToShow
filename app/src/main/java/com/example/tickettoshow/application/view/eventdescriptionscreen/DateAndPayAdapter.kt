package com.example.tickettoshow.application.view.eventdescriptionscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoshow.application.model.event.DataDateAndPay
import com.example.tickettoshow.databinding.ItemDateAndPayRecyclerviewBinding

class DateAndPayAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<DateAndPayAdapter.DateAndPayViewHolder>(), View.OnClickListener {

    private val _dates = mutableListOf<DataDateAndPay>()
    val dates: List<DataDateAndPay> = _dates

    @SuppressLint("NotifyDataSetChanged")
    fun addDates(dates: List<DataDateAndPay>) {
        _dates.addAll(dates)
        notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        val data = v.tag as DataDateAndPay
        listener.onPayClick(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DateAndPayViewHolder(
        ItemDateAndPayRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: DateAndPayViewHolder, position: Int) {
        val date = _dates[position]
        with(holder.binding) {
            itemPayButton.text = date.minPrice
            itemDateTextview.text = date.date
            itemTimeTextview.text = date.time
            itemPayButton.setOnClickListener(this@DateAndPayAdapter)
            itemPayButton.tag = date
        }
    }


    override fun getItemCount() = _dates.size


    class DateAndPayViewHolder(
        val binding: ItemDateAndPayRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onPayClick(data: DataDateAndPay)
    }
}