package com.example.tickettoshow.application.view.homescreen

import com.example.tickettoshow.application.App
import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.toDataEvent
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment
import com.example.tickettoshow.foundation.BaseApplication
import com.example.tickettoshow.foundation.model.SuccessResult
import com.example.tickettoshow.foundation.model.takeSuccess
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult


class HomeScreenViewModel(
    private val app: BaseApplication,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository
) : BaseViewModel(), ConcertEventAdapter.Listener {

    private val _events = MutableLiveResult<List<DataEvent>>(PendingResult())
    val events: LiveResult<List<DataEvent>> = _events

    private val _addingToFavorite = MutableLiveResult<Boolean>(SuccessResult(false))
    val addingToFavorite: LiveResult<Boolean> = _addingToFavorite

//    private val eventListener: EventListener = {
//        _events.postValue(SuccessResult(it))
//    }

    init {
//        eventRepository.addListener(eventListener)
        load()
    }

    override fun onCleared() {
        super.onCleared()
//        eventRepository.removeListener(eventListener)
    }

    private fun load() = into(_events) {
        eventRepository.getEvents()
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    override fun onFavoriteClick(event: DataEvent) {
        val currUser = (app as App).currentUser
        if (currUser != null)
            into(_addingToFavorite) {
                userRepository.addEventToFavorite(currUser.id, event)
            }
    }

    override fun onEventClick(event: DataEvent) {
        val screen = EventDescriptionFragment.Screen(event.id)
        navigator.launch(screen)
    }

    fun onTryAgain() {
        load()
    }

}