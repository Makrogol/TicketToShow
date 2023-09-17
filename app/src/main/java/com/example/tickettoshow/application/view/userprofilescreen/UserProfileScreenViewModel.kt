package com.example.tickettoshow.application.view.userprofilescreen

import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.model.user.sharedpref.UserTokenSharedPref
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult

class UserProfileScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: UserRepository,
    private val sharedPref: UserTokenSharedPref
) : BaseViewModel() {

    private val _updateUser = MutableLiveResult<Unit>(PendingResult())
    var updateUser: LiveResult<Unit> = _updateUser

    private val _currentUser = MutableLiveResult<DataUser?>(PendingResult())
    var currentUser: LiveResult<DataUser?> = _currentUser


    fun updateUserData(
        user: DataUser,
        email: String? = null,
        name: String? = null,
        password: String? = null
    ) {
        _updateUser.postValue(PendingResult())
        into(_updateUser) {
            updateCurrentUserToken(repository.updateUserData(user, email, name, password))
        }
    }

    fun loadUser() {
        into(_currentUser) {
            repository.getUserByToken(getUserToken()!!)
        }
    }

    private fun updateCurrentUserToken(token: String) {
        sharedPref.putToken(token)
    }

    private fun getUserToken(): String? {
        return sharedPref.getToken()
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }

    fun toast(message: String) {
        uiActions.toast(message)
    }
}