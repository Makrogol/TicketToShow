package com.example.tickettoshow.application.repository.event

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.foundation.model.Repository
import com.example.tickettoshow.foundation.tasks.Task

typealias EventListener = (List<DataEvent>) -> Unit

interface EventRepository : Repository {

    fun getEvents(): Task<List<DataEvent>>

    fun getEventById(id: Long): Task<DataEventDescription>

    fun addListener(listener: EventListener)

    fun removeListener(listener: EventListener)
}