package com.example.tickettoshow.foundation.model.dispatchers

interface Dispatcher {

    fun dispatch(block: () -> Unit)

}