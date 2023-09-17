package com.example.tickettoshow.application.view.eventdescriptionscreen

import com.example.tickettoshow.application.model.event.DataDateAndPay
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.application.view.eventdescriptionscreen.EventDescriptionFragment.Screen
import com.example.tickettoshow.foundation.model.Result
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.model.SuccessResult
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.application.model.event.toDataEvent
import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.model.user.sharedpref.UserTokenSharedPref
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.application.view.webviewscreen.WebviewScreenFragment
import com.example.tickettoshow.foundation.model.takeSuccess
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult


class EventDescriptionViewModel(
    screen: Screen,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository,
    private val sharedPref: UserTokenSharedPref
) : BaseViewModel(), DateAndPayAdapter.Listener {

    private val _result = MutableLiveResult<DataEventDescription>(PendingResult())
    val result: LiveResult<DataEventDescription> = _result

    private val _addingToFavorite = MutableLiveResult<Boolean>(SuccessResult(false))
    val addingToFavorite: LiveResult<Boolean> = _addingToFavorite

    private val _currentUser = MutableLiveResult<DataUser?>(PendingResult())
    val currentUser: LiveResult<DataUser?> = _currentUser

    private val currentResult: Result<DataEventDescription> get() = result.value!!

    init {
        loadEventById(screen.eventId)
        loadUser()
    }

    private fun loadEventById(id: Long) {
        if (currentResult is SuccessResult) return

        into(_result) {
            eventRepository.getEventById(id)
        }
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    private fun loadUser()  = into(_currentUser) {
        userRepository.getUserByToken(sharedPref.getToken())
    }

    fun addEventToFavorite(userId: Long) = into(_addingToFavorite) {
        userRepository.addEventToFavorite(userId, _result.value.takeSuccess()!!.toDataEvent())
    }

    override fun onPayClick(data: DataDateAndPay) {
        uiActions.toast("Оплатите заказ дата: ${data.date}\nвремя: ${data.time}\nстоимость: ${data.minPrice}")

        val url = "https://www.google.com"
        launch(WebviewScreenFragment.Screen(url))
    }
}