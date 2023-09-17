package com.example.tickettoshow.application.view.eventdescriptionscreen

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.R
import com.example.tickettoshow.application.model.event.DataDateAndPay
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.application.onTryAgain
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.databinding.FragmentDescriptionEventBinding
import com.example.tickettoshow.foundation.views.screenViewModel
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.application.view.CalendarFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class EventDescriptionFragment : BaseFragment(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen(
        val eventId: Long
    ) : BaseScreen

    private lateinit var binding: FragmentDescriptionEventBinding
    override val viewModel by screenViewModel<EventDescriptionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionEventBinding.inflate(inflater, container, false)
        val adapter = DateAndPayAdapter(viewModel)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_home_screen).setIcon(R.drawable.affiche_on_icon)
        binding.dateAndPayRecyclerview.adapter = adapter

        viewModel.result.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = { data ->
                    setData(binding, data)
                    adapter.addDates(
                        listOf(
                            DataDateAndPay(
                                date = data.event.date,
                                time = data.event.time,
                                minPrice = "Купить билет от\n1000p"
                            )
                        )
                    )
                }
            )
        }

        onTryAgain(binding.root) {
            viewModel.tryAgain()
        }

        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)
        return binding.root
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        else -> false
    }


    private fun setData(binding: FragmentDescriptionEventBinding, data: DataEventDescription) {
        with(binding) {
            typeEventTextview.text = "Концерты"
            placeEventTextview.text = "Дом музыки"
            nameEventTextview.text = data.event.name
            ageLimitTextview.text = "12+"
            posterEventImageview.setImageResource(data.event.posterId)
            descriptionEventTextview.text = data.description
            descriptionEventTextview.movementMethod = ScrollingMovementMethod()
            shortAddressEventTextview.text = "Дом музыки"
            addressTextTextview.text = "Адрес"
            addressTextview.text = data.event.address
        }
    }
}