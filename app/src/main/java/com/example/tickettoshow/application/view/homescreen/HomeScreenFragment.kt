package com.example.tickettoshow.application.view.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.R
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.databinding.FragmentHomeScreenBinding
import com.example.tickettoshow.foundation.views.screenViewModel
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.application.view.CalendarFragment
import com.example.tickettoshow.application.view.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeScreenFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen: BaseScreen

    private lateinit var binding: FragmentHomeScreenBinding
    override val viewModel by screenViewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        val adapter = ConcertEventAdapter(viewModel)

        binding.concertsRecyclerview.adapter = adapter
        viewModel.getEvents()

        viewModel.events.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = {
                    adapter.addShows(it)
                }
            )
        }

        return binding.root
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarFragment.Screen())
        else -> false
    }
}