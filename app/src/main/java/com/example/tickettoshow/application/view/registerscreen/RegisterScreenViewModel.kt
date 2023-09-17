package com.example.tickettoshow.application.view.registerscreen

import com.example.tickettoshow.application.model.user.api.DataUser
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

    private val _userRegistered = MutableLiveResult<DataUser>(PendingResult())
    val userRegistered: LiveResult<DataUser> = _userRegistered

    private val _userChecked = MutableLiveResult<Boolean>(PendingResult())
    val userChecked: LiveResult<Boolean> = _userChecked


    fun checkUserByEmail(email: String) = into(_userChecked) {
        userRepository.checkEmailIsUnic(email)
    }

    fun registerUser(user: DataUser) = into(_userRegistered) {
        _userRegistered.postValue(PendingResult())
        userRepository.registerUser(user)
    }

    fun launch(screen: BaseScreen) {
        navigator.launch(screen)
    }

    fun toast(text: String) {
        uiActions.toast(text)
    }
}