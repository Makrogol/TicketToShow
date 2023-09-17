package com.example.tickettoshow.application.model.event

import com.example.tickettoshow.application.view.homescreen.ConcertEventAdapter

data class DataTypeEvents(
    val adapter: ConcertEventAdapter,
    val eventsName: String
)