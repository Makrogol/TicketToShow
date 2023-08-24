package com.example.tickettoshow.application.view.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.tasks.ErrorResult
import com.example.tickettoshow.foundation.tasks.PendingResult
import com.example.tickettoshow.foundation.tasks.Result
import com.example.tickettoshow.foundation.tasks.SuccessResult
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment


class HomeViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository
) : BaseViewModel(), ConcertEventAdapter.Listener {

    private val _events = MutableLiveData<Result<List<EventListItem>>>()
    val events: LiveData<Result<List<EventListItem>>> = _events

    private val eventIdsInProgress = mutableSetOf<Long>()
    private var eventResult: Result<List<DataEvent>> = PendingResult()
        set(value) {
            field = value
            notifyUpdates()
        }

    init {
        getEvents()
        notifyUpdates()
    }

    fun getEvents() {
        eventResult = PendingResult()
        eventRepository.getEvents()
            .onError {
                eventResult = ErrorResult(it)
                uiActions.toast("Error home screen view model")
            }
            .onSuccess {
                eventResult = SuccessResult(it)
            }
            .autoCancel()
    }


    private fun addProgressTo(event: DataEvent) {
        eventIdsInProgress.add(event.id)
        notifyUpdates()
    }

    private fun removeProgressFrom(event: DataEvent) {
        eventIdsInProgress.remove(event.id)
        notifyUpdates()
    }

    private fun isInProgress(event: DataEvent): Boolean {
        return eventIdsInProgress.contains(event.id)
    }


    private fun notifyUpdates() {
        _events.postValue(eventResult.map { event->
            event.map { EventListItem(it, isInProgress(it)) }
        })
    }

    fun launch(screen: BaseScreen) : Boolean {
        navigator.launch(screen)
        return true
    }

    override fun onEventClick(event: DataEvent) {
        val screen = EventDescriptionFragment.Screen(event.id)
        navigator.launch(screen)
    }

}