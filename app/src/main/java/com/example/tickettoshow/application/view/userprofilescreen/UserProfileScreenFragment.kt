package com.example.tickettoshow.application.view.userprofilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.currentComposer
import androidx.core.widget.doAfterTextChanged
import com.example.tickettoshow.R
import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.renderSimpleResult
import com.example.tickettoshow.application.view.calendarscreen.CalendarScreenFragment
import com.example.tickettoshow.application.view.errorscreen.ErrorScreenFragment
import com.example.tickettoshow.application.view.changepasswordscreen.ChangePasswordFragment
import com.example.tickettoshow.application.view.homescreen.HomeScreenFragment
import com.example.tickettoshow.application.view.profilescreen.ProfileFragment
import com.example.tickettoshow.databinding.FragmentUserProfileScreenBinding
import com.example.tickettoshow.foundation.views.BaseFragment
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.screenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserProfileScreenFragment : BaseFragment(),
    BottomNavigationView.OnNavigationItemSelectedListener {


    class Screen : BaseScreen

    private lateinit var binding: FragmentUserProfileScreenBinding
    override val viewModel by screenViewModel<UserProfileScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileScreenBinding.inflate(inflater, container, false)
        binding.bottomNavMenu.menu.findItem(R.id.bot_menu_profile_screen)
            .setIcon(R.drawable.profile_on_icon)
        binding.bottomNavMenu.setOnNavigationItemSelectedListener(this)


        viewModel.loadUser()
        var currentUser: DataUser? = null

        viewModel.currentUser.observe(viewLifecycleOwner) { result->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onError = {viewModel.launch(ErrorScreenFragment.Screen())},
                onSuccess = {user ->
                    currentUser = user
                }
            )
        }

        checkUserAccount(currentUser)
        var name_changed = false
        var email_changed = false

        with(binding) {
            changePasswordTextview.setOnClickListener {
                viewModel.launch(ChangePasswordFragment.Screen())
            }

            nameEdittext.doAfterTextChanged {
                if (it?.toString() != currentUser!!.name) {
                    name_changed = true
                    doSaveButtonOn()
                }
            }
            emailEdittext.doAfterTextChanged {
                if (it?.toString() != currentUser!!.email) {
                    email_changed = true
                    doSaveButtonOn()
                }
            }
            backButton.setOnClickListener {
                if (email_changed or name_changed) {
                    exitLayout.visibility = View.VISIBLE
                    exitLayout.bringToFront()
                } else
                    viewModel.launch(ProfileFragment.Screen())
            }
            yesLogoutButton.setOnClickListener {
                viewModel.launch(ProfileFragment.Screen())
            }
            noLogoutButton.setOnClickListener {
                exitLayout.visibility = View.GONE
            }
            saveButton.setOnClickListener {
                clearFocusEditText()

                var new_name = currentUser!!.name
                var new_email = currentUser!!.email
                if (email_changed)
                    new_email = emailEdittext.text.toString()
                if (name_changed)
                    new_name = nameEdittext.text.toString()

                email_changed = false
                name_changed = false
                viewModel.updateUserData(
                    user = currentUser!!,
                    email = new_email,
                    name = new_name
                )

                viewModel.updateUser.observe(viewLifecycleOwner) {
                    renderSimpleResult(
                        root = binding.root,
                        result = it,
                        onError = { viewModel.launch(ErrorScreenFragment.Screen()) },
                        onSuccess = {
                            viewModel.toast("Ваши данные были успешно сохранены")
                            viewModel.loadUser()
                            binding.exitLayout.visibility = View.GONE
                            checkUserAccount(currentUser)
                            doSaveButtonOff()
                        }
                    )
                }
            }
        }


        return binding.root
    }


    private fun clearFocusEditText() {
        with(binding) {
            emailEdittext.clearFocus()
            nameEdittext.clearFocus()
        }
    }

    private fun checkUserAccount(user: DataUser?) {
        if (user != null) {
            with(binding) {
                emailTextview.text = user.email
                nameEdittext.setText(user.name)
                emailEdittext.setText(user.email)
                if (user.name.isNotEmpty())
                    nameTextview.text = user.name
            }
        }
    }

    private fun doSaveButtonOn() {
        binding.saveButton.visibility = View.VISIBLE
    }

    private fun doSaveButtonOff() {
        binding.saveButton.visibility = View.GONE
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bot_menu_home_screen -> viewModel.launch(HomeScreenFragment.Screen())
        R.id.bot_menu_profile_screen -> viewModel.launch(ProfileFragment.Screen())
        R.id.bot_menu_calendar_screen -> viewModel.launch(CalendarScreenFragment.Screen())
        else -> false
    }
}