package com.example.tickettoshow.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.model.DataEvent
import com.example.tickettoshow.R
import com.example.tickettoshow.databinding.FragmentDescriptionEventBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DescriptionEventFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentDescriptionEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDescriptionEventBinding.inflate(inflater, container, false)
        val adapter = DateAndPayAdapter()
        binding.dateAndPayRecyclerview.adapter = adapter

        val event = arguments?.getSerializable(ARG_EVENT_DATA) as DataEvent

        with(binding){
            typeEventTextview.text = "Концерты"
            placeEventTextview.text = "Дом музыки"
            nameEventTextview.text = event.name
            ageLimitTextview.text = "12+"
            posterEventImageview.setImageResource(event.posterId)
            descriptionEventTextview.text = event.description
            descriptionTextTextview.text = "Описание"
            shortAddressEventTextview.text = "Дом музыки"
            addressTextTextview.text = "Адрес"
            addressTextview.text = event.address
        }


        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)
        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> routeTo(HomeScreenFragment.newInstance())
        R.id.bot_menu_profile_screen -> routeTo(ProfileFragment.newInstance())
        R.id.bot_menu_calendar_screen -> routeTo(CalendarFragment.newInstance())
        else -> false
    }

    private fun routeTo(fragment: Fragment): Boolean {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

        return true
    }

    companion object {

        private const val ARG_EVENT_DATA = "arg_event_data"

        @JvmStatic
        fun newInstance(event: DataEvent) = DescriptionEventFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_EVENT_DATA, event)
            }
        }
    }
}