package com.example.tickettoshow.application.view.eventdescriptionscreen

import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment.Screen
import com.example.tickettoshow.foundation.tasks.Result
import com.example.tickettoshow.foundation.tasks.PendingResult
import com.example.tickettoshow.foundation.tasks.SuccessResult
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult


class EventDescriptionViewModel(
    screen: Screen,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository
) : BaseViewModel() {

    private val _result = MutableLiveResult<DataEventDescription>()
    val result: LiveResult<DataEventDescription> = _result

    private val currentResult: Result<DataEventDescription> get() = result.value!!

    init {
        _result.value = PendingResult()
        loadEventById(screen.eventId)
    }

    private fun loadEventById(id: Long) {
        if (currentResult is SuccessResult) return

        _result.postValue(PendingResult())

        eventRepository.getEventById(id)
            .onSuccess {
                _result.postValue(SuccessResult(it))
            }
            .onError {
                uiActions.toast("Error event description view model")
            }
            .autoCancel()
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    fun tryAgain() {
        //todo
    }
}