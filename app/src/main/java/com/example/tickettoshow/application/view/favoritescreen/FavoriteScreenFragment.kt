package com.example.tickettoshow.application.view.favoritescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.R
import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.calendarscreen.CalendarScreenFragment
import com.example.tickettoshow.application.view.entryscreen.EntryScreenFragment
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.example.tickettoshow.databinding.FragmentFavoriteScreenBinding
import com.example.tickettoshow.databinding.FragmentProfileBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteScreenFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {
    class Screen : BaseScreen


    private lateinit var binding: FragmentFavoriteScreenBinding
    override val viewModel by screenViewModel<FavoriteScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_profile_screen)
            .setIcon(R.drawable.profile_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        val adapter = FavoriteEventAdapter(viewModel)
        binding.favoriteRecyclerView.adapter = adapter

        viewModel.events.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                onSuccess = { ListDataEvent ->
                    adapter.addShows(ListDataEvent)
                }
            )
        }

        binding.backButton.setOnClickListener {
            viewModel.launch(ProfileFragment.Screen())
        }
        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarScreenFragment.Screen())
        else -> false
    }
}