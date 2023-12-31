package com.example.tickettoshow.application.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.R
import com.example.tickettoshow.databinding.FragmentProfileBinding
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen: BaseScreen

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
//        when(item.itemId) {
//        R.id.bot_menu_home_screen -> routeTo(HomeScreenFragment.newInstance())
//        R.id.bot_menu_profile_screen -> routeTo(newInstance())
//        R.id.bot_menu_calendar_screen -> routeTo(CalendarFragment.newInstance())
//        else -> false
//    }

    private fun routeTo(fragment: Fragment): Boolean {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

        return true
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}