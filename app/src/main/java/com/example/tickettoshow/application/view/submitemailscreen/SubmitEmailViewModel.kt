package com.example.tickettoshow.application.view.submitemailscreen

import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult

class SubmitEmailViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _checkCode = MutableLiveResult<Boolean>(PendingResult())
    val checkCode: LiveResult<Boolean> = _checkCode


    fun launch(screen: BaseScreen) {
        navigator.launch(screen)
    }

}