package com.example.tickettoshow.application.view.homescreen

import com.example.tickettoshow.application.model.event.DataEvent

data class EventListItem(
    val event: DataEvent,
    val isInProgress: Boolean
)