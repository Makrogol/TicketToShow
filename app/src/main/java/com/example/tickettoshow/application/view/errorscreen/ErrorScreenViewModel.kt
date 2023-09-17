package com.example.tickettoshow.application.view.errorscreen

import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel

class ErrorScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: EventRepository
) : BaseViewModel() {

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }
}