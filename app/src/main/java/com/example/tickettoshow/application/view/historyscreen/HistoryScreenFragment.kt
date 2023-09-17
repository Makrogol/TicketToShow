package com.example.tickettoshow.application.view.historyscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.tickettoshow.R
import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.calendarscreen.CalendarScreenFragment
import com.example.tickettoshow.application.view.changepasswordscreen.ChangePasswordViewModel
import com.example.tickettoshow.application.view.entryscreen.EntryScreenFragment
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.example.tickettoshow.databinding.FragmentChangePasswordScreenBinding
import com.example.tickettoshow.databinding.FragmentHistoryScreenBinding
import com.example.tickettoshow.databinding.FragmentHomeScreenBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryScreenFragment: BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {
    class Screen : BaseScreen

    private lateinit var binding: FragmentHistoryScreenBinding
    override val viewModel by screenViewModel<HistoryScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_profile_screen).setIcon(R.drawable.profile_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)
        val adapter = HistoryEventAdapter(viewModel)
        binding.historyRecyclerView.adapter = adapter

        viewModel.history.observe(viewLifecycleOwner) {result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                onSuccess = { history ->
                    adapter.addShows(history)
                }
            )
        }

        with(binding) {
            backButton.setOnClickListener {
                viewModel.launch(ProfileFragment.Screen())
            }
        }

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarScreenFragment.Screen())
        else -> false
    }
}