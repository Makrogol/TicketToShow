package com.example.tickettoshow.application.view.submitemailscreen

import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel

class SubmitEmailViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions
): BaseViewModel() {




    fun launch(screen: BaseScreen) {
        navigator.launch(screen)
    }

}