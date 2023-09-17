package com.example.tickettoshow.application.view.entryscreen

import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.model.user.sharedpref.UserTokenSharedPref
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.MutableLiveResult
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.model.SuccessResult
import com.example.tickettoshow.foundation.views.LiveResult
import kotlinx.coroutines.launch
import java.lang.Exception

class EntryScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: UserRepository,
    private val sharedPref: UserTokenSharedPref
) : BaseViewModel() {

    private val _user = MutableLiveResult<Pair<String?, String>>(PendingResult())
    val user: LiveResult<Pair<String?, String>> = _user

    fun updateCurrentUserToken(token: String) {
        sharedPref.putToken(token)
    }


    fun logInUser(email: String, password: String) = into(_user) {
        _user.postValue(PendingResult())
        repository.logInUser(email, password)
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }
}