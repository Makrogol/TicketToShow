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
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult


class EventDescriptionViewModel(
    screen: Screen,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository,
) : BaseViewModel(), DateAndPayAdapter.Listener {

    private val _result = MutableLiveResult<DataEventDescription>(PendingResult())
    val result: LiveResult<DataEventDescription> = _result

    private val currentResult: Result<DataEventDescription> get() = result.value!!

    init {
        loadEventById(screen.eventId)
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

    fun tryAgain() {
        //todo
    }

    override fun onPayClick(data: DataDateAndPay) {
        uiActions.toast("Оплатите заказ дата: ${data.date}\nвремя: ${data.time}\nстоимость: ${data.minPrice}")
    }
}