package com.example.tickettoshow.application.repository.event

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.application.model.event.HistoryEvent
import com.example.tickettoshow.foundation.model.Repository

typealias EventListener = (List<DataEvent>) -> Unit

interface EventRepository : Repository {

    suspend fun getEvents(): List<DataEvent>

    suspend fun getEventById(id: Long): DataEventDescription

    fun addListener(listener: EventListener)

    fun removeListener(listener: EventListener)
}