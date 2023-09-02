package com.example.tickettoshow.application.view.registerscreen

import android.app.PendingIntent
import com.example.tickettoshow.application.model.user.DataUser
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult

class RegisterScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userRegistered = MutableLiveResult<Unit>(PendingResult())
    val userRegistered: LiveResult<Unit> = _userRegistered

    private val _userChecked = MutableLiveResult<Boolean>(PendingResult())
    val userChecked: LiveResult<Boolean> = _userChecked


    fun checkUserByEmail(email: String) = into(_userChecked) {
        userRepository.checkUserByEmail(email)
    }

    fun registerUser(user: DataUser) = into(_userRegistered) {
        userRepository.registerUser(user)
    }

    fun launch(screen: BaseScreen) {
        navigator.launch(screen)
    }

}