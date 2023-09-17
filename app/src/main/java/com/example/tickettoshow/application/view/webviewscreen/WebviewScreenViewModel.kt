package com.example.tickettoshow.application.view.webviewscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tickettoshow.application.repository.event.EventRepository
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.application.view.webviewscreen.WebviewScreenFragment.Screen
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.model.SuccessResult
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult

class WebviewScreenViewModel(
    screen: Screen,
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val eventRepository: EventRepository,
) : BaseViewModel() {

    private val _url = MutableLiveResult<String>(PendingResult())
    val url: LiveResult<String> = _url

    init {
        loadUrl(screen.url)
    }

    private fun loadUrl(url: String) {
        _url.postValue(SuccessResult(url))
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }
}