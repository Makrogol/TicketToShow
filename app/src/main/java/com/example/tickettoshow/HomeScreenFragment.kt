package com.example.tickettoshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.databinding.FragmentHomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeScreenFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val adapter = ConcertShowAdapter()
        binding.concertsRecyclerview.adapter = adapter
//        adapter.addShows(listOf(
//            DataShow(
//
//            )
//        ))
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)
        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when(item.itemId) {
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

        @JvmStatic
        fun newInstance() = HomeScreenFragment()
    }
}