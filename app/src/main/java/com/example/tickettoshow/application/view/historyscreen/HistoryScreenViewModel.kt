package com.example.tickettoshow.application.view.historyscreen

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.application.model.event.HistoryEvent
import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.model.user.sharedpref.UserTokenSharedPref
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment
import com.example.tickettoshow.application.view.homescreen.ConcertEventAdapter
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.model.takeSuccess
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult

class HistoryScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: UserRepository,
    private val sharedPref: UserTokenSharedPref
) : BaseViewModel(), HistoryEventAdapter.Listener {

    private val _history = MutableLiveResult<List<HistoryEvent>>(PendingResult())
    val history: LiveResult<List<HistoryEvent>> = _history

    private val _currentUser = MutableLiveResult<DataUser?>(PendingResult())
//    val currentUser: LiveResult<DataUser?> = _currentUser

    init {
        loadUser()
        load(_currentUser.value.takeSuccess()!!.id)
    }

    private fun loadUser() = into(_currentUser) {
        repository.getUserByToken(sharedPref.getToken())
    }

    private fun load(userId: Long) = into(_history) {
        repository.getHistory(userId)
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    override fun onEventClick(event: HistoryEvent) {
        uiActions.toast("Билет для события ${event.name}")
    }
}