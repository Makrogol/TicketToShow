package com.example.tickettoshow.application.view.homescreen

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.repository.event.EventListener
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.model.tasks.ErrorResult
import com.example.tickettoshow.foundation.model.tasks.PendingResult
import com.example.tickettoshow.foundation.model.tasks.SuccessResult
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment
import com.example.tickettoshow.foundation.model.tasks.dispatchers.Dispatcher
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult


class HomeScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository,
    dispatcher: Dispatcher
) : BaseViewModel(dispatcher), ConcertEventAdapter.Listener {

    private val _events = MutableLiveResult<List<DataEvent>>(PendingResult())
    val events: LiveResult<List<DataEvent>> = _events

    private val eventListener: EventListener = {
        _events.postValue(SuccessResult(it))
    }

    init {
        eventRepository.addListener(eventListener)
        load()
    }

    override fun onCleared() {
        super.onCleared()
        eventRepository.removeListener(eventListener)
    }

    private fun load() {
        eventRepository.getEvents().into(_events)
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    override fun onEventClick(event: DataEvent) {
        val screen = EventDescriptionFragment.Screen(event.id)
        navigator.launch(screen)
    }

    fun onTryAgain() {
        load()
    }

}