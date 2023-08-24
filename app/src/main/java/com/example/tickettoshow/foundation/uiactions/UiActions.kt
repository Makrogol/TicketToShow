package com.example.tickettoshow.foundation.uiactions

interface UiActions {

    fun toast(message: String)

    fun getString(messageRes: Int, vararg args: Any): String

}