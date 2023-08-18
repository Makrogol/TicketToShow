package com.example.tickettoshow.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tickettoshow.repository.DependencyProvider
import com.example.tickettoshow.repository.DependencyProviderImpl
import com.example.tickettoshow.viewmodel.MainViewModel
import com.example.tickettoshow.R
import com.example.tickettoshow.databinding.FragmentHomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeScreenFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var viewModel: MainViewModel
    private val provider: DependencyProvider = DependencyProviderImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        val adapter = ConcertEventAdapter {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, DescriptionEventFragment.newInstance(it))
                .commit()
        }
        binding.concertsRecyclerview.adapter = adapter

        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        viewModel.repository = provider.provideMainRepository()
        viewModel.getEvents()
        viewModel.events.observe(viewLifecycleOwner) {
            adapter.addShows(it)
        }


        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.bot_menu_home_screen -> routeTo(newInstance())
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

        @JvmStatic
        fun newInstance() = HomeScreenFragment()
    }
}