package com.example.tickettoshow.application.view.profilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.R
import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.view.calendarscreen.CalendarScreenFragment
import com.example.tickettoshow.application.view.entryscreen.EntryScreenFragment
import com.example.tickettoshow.application.view.favoritescreen.FavoriteScreenFragment
import com.example.tickettoshow.application.view.historyscreen.HistoryScreenFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.registerscreen.RegisterScreenFragment
import com.example.tickettoshow.application.view.userprofilescreen.UserProfileScreenFragment
import com.example.tickettoshow.databinding.FragmentProfileBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.screenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    class Screen : BaseScreen


    private lateinit var binding: FragmentProfileBinding
    override val viewModel by screenViewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_profile_screen)
            .setIcon(R.drawable.profile_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)

        checkUserAccount()
        var currentUser: DataUser? = null

        with(binding) {
            historyButton.setOnClickListener {
                viewModel.launch(HistoryScreenFragment.Screen())
            }

            registerButton.setOnClickListener {
                viewModel.launch(RegisterScreenFragment.Screen())
            }

            entryButton.setOnClickListener {
                viewModel.launch(EntryScreenFragment.Screen())
            }

            logOutButton.setOnClickListener {
                logOutLayout.visibility = View.VISIBLE
                changeStateButtons(false)
                logOutLayout.bringToFront()
            }

            yesLogoutButton.setOnClickListener {
                (requireActivity().application as App).currentUser = null
                viewModel.launch(EntryScreenFragment.Screen())
            }

            noLogoutButton.setOnClickListener {
                hideAnotherLayout()
            }

            supportButton.setOnClickListener {
                supportLayout.visibility = View.VISIBLE
                changeStateButtons(false)
                supportLayout.bringToFront()
            }

            supportLayoutCloseImg.setOnClickListener {
                hideAnotherLayout()
            }

            defaultLayout.setOnClickListener {
                hideAnotherLayout()
            }

            profileButton.setOnClickListener {
                viewModel.launch(UserProfileScreenFragment.Screen())
            }

            favoriteButton.setOnClickListener {
                viewModel.launch(FavoriteScreenFragment.Screen())
            }
        }


        return binding.root
    }


    private fun hideAnotherLayout() {
        binding.logOutLayout.visibility = View.GONE
        binding.supportLayout.visibility = View.GONE
        changeStateButtons(true)
    }


    private fun changeStateButtons(state: Boolean) = with(binding) {
        registerButton.isEnabled = state
        entryButton.isEnabled = state
        profileButton.isEnabled = state
        favoriteButton.isEnabled = state
        historyButton.isEnabled = state
        logOutButtonLayout.isEnabled = state
        infoButton.isEnabled = state
        supportButton.isEnabled = state
    }

    private fun doAllGone() {
        with(binding) {
            profileButton.visibility = View.GONE
            favoriteButton.visibility = View.GONE
            historyButton.visibility = View.GONE
            emailTextview.visibility = View.GONE
            nameTextview.visibility = View.GONE
            logOutButtonLayout.visibility = View.GONE
            logInLayout.visibility = View.GONE
        }
    }

    private fun checkUserAccount() {
        doAllGone()
        val user = (requireActivity().application as App).currentUser
        with(binding) {
            if (user != null) {
                profileButton.visibility = View.VISIBLE
                favoriteButton.visibility = View.VISIBLE
                historyButton.visibility = View.VISIBLE
                emailTextview.visibility = View.VISIBLE
                nameTextview.visibility = View.VISIBLE
                logOutButton.visibility = View.VISIBLE
                emailTextview.text = user.email
                if (user.name.isNotEmpty())
                    nameTextview.text = user.name
            } else
                logInLayout.visibility = View.VISIBLE
        }
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarScreenFragment.Screen())
        else -> false
    }
}