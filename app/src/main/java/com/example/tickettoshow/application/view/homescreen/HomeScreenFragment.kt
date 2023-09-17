package com.example.tickettoshow.application.view.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.R
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.databinding.FragmentHomeScreenBinding
import com.example.tickettoshow.foundation.views.screenViewModel
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.application.view.CalendarFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeScreenFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen : BaseScreen

    private lateinit var binding: FragmentHomeScreenBinding
    override val viewModel by screenViewModel<HomeScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_home_screen).setIcon(R.drawable.affiche_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        val adapter = TypeEventsAdapter()
        val adapters = createAdapters()

        binding.typeEventRecyclerview.adapter = adapter

        viewModel.events.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = { ListDataEvent ->
                    adapters.forEach { it.adapter.addShows(ListDataEvent) }
                    adapter.addTypeEvents(adapters)
                }
            )
        }

        return binding.root
    }

    private fun createAdapters(): List<DataTypeEvents> = listOf(
        DataTypeEvents(
            adapter = ConcertEventAdapter(viewModel),
            eventsName = "Концерты"
        ),
        DataTypeEvents(
            adapter = ConcertEventAdapter(viewModel),
            eventsName = "Классика"
        ),
        DataTypeEvents(
            adapter = ConcertEventAdapter(viewModel),
            eventsName = "Спектакли"
        ),
        DataTypeEvents(
            adapter = ConcertEventAdapter(viewModel),
            eventsName = "Для детей"
        ),
    )


    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarFragment.Screen())
        else -> false
    }
}