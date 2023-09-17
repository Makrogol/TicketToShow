package com.example.tickettoshow.application.view.profilescreen

import com.example.tickettoshow.application.model.user.sharedpref.UserTokenSharedPref
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel

class ProfileViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: UserRepository,
    private val sharedPref: UserTokenSharedPref
) : BaseViewModel() {


    fun logOutUser() {
        sharedPref.putToken("")
    }


    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }
}