package com.example.tickettoshow.application.view.eventdescriptionscreen

import androidx.compose.animation.scaleIn
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment.Screen
import com.example.tickettoshow.foundation.model.tasks.Result
import com.example.tickettoshow.foundation.model.tasks.PendingResult
import com.example.tickettoshow.foundation.model.tasks.SuccessResult
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.foundation.model.tasks.dispatchers.Dispatcher
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult


class EventDescriptionViewModel(
    screen: Screen,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository,
    dispatcher: Dispatcher
) : BaseViewModel(dispatcher) {

    private val _result = MutableLiveResult<DataEventDescription>(PendingResult())
    val result: LiveResult<DataEventDescription> = _result

    private val currentResult: Result<DataEventDescription> get() = result.value!!

    init {
        loadEventById(screen.eventId)
    }

    private fun loadEventById(id: Long) {
        if (currentResult is SuccessResult) return

        eventRepository.getEventById(id).into(_result)
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    fun tryAgain() {
        //todo
    }
}