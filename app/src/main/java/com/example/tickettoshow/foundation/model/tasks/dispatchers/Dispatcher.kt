package com.example.tickettoshow.foundation.model.tasks.dispatchers

interface Dispatcher {

    fun dispatch(block: () -> Unit)

}