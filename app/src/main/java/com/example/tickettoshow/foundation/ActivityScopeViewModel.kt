package com.example.tickettoshow.foundation

import androidx.lifecycle.ViewModel
import com.example.tickettoshow.foundation.navigator.IntermediateNavigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.navigator.Navigator

const val ARG_SCREEN = "ARG_SCREEN"

// Вью модель для активити

class ActivityScopeViewModel(
    val uiActions: UiActions,
    val navigator: IntermediateNavigator
) : ViewModel(),
    Navigator by navigator,
    UiActions by uiActions {

    override fun onCleared() {
        super.onCleared()
        navigator.clear()
    }

}