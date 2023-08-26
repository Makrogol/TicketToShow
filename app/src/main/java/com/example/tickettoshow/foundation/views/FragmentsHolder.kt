package com.example.tickettoshow.foundation.views

import com.example.tickettoshow.foundation.ActivityScopeViewModel

// Базовый класс для активити

interface FragmentsHolder {

    fun notifyScreenUpdates()

    fun getActivityScopeViewModel() : ActivityScopeViewModel
}