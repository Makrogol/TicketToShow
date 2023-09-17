package com.example.tickettoshow.application.view.calendarscreen

import com.example.tickettoshow.application.repository.user.UserRepository
import com.example.tickettoshow.foundation.navigator.Navigator
import com.example.tickettoshow.foundation.uiactions.UiActions
import com.example.tickettoshow.foundation.views.BaseScreen
import com.example.tickettoshow.foundation.views.BaseViewModel
import java.util.Calendar

class CalendarScreenViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val repository: UserRepository
) : BaseViewModel() {


    fun calculateCurrData(): IntArray {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return arrayOf(
            year,
            month,
            calculateStartMonth(day, (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7)
        ).toIntArray()
    }

    fun calculateStartMonth(day: Int, dayOfWeekStartMonth: Int): Int {
        return (dayOfWeekStartMonth - (day) % 7 + 7) % 7
    }

    fun launch(screen: BaseScreen): Boolean {
        navigator.launch(screen)
        return true
    }
}