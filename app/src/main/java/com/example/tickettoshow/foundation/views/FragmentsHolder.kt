package com.example.tickettoshow.foundation.views

import com.example.tickettoshow.foundation.ActivityScopeViewModel

interface FragmentsHolder {

    fun notifyScreenUpdates()

    fun getActivityScopeViewModel() : ActivityScopeViewModel
}