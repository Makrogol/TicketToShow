package com.example.tickettoshow.application.view.changepasswordscreen

import androidx.lifecycle.MutableLiveData
import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.model.PendingResult
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import com.example.tickettoshow.foundation.views.LiveResult
import com.example.tickettoshow.foundation.views.MutableLiveResult

class ChangePasswordViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: UserRepository,
) : BaseViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    private val _is_password_changed = MutableLiveResult<Unit>(PendingResult())
    val is_password_changed: LiveResult<Unit> = _is_password_changed

    private val _emailChecked = MutableLiveResult<Boolean>(PendingResult())
    val emailChecked: LiveResult<Boolean> = _emailChecked

    fun checkEmail() = into(_emailChecked) {
        !repository.checkEmailIsUnic(_email.value!!)
    }

    fun setEmail(email: String) {
        _email.postValue(email)
    }

    fun setPassword(password: String) {
        _password.postValue(password)
    }

    fun changeUserPassword() = into(_is_password_changed) {
        repository.changePassword(_email.value!!, _password.value!!)
    }


    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }
}