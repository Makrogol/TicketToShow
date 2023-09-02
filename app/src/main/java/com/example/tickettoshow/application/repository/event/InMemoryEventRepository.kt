package com.example.tickettoshow.application.repository.event

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.application.model.event.EventsApi
import com.example.tickettoshow.foundation.model.coroutines.IoDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

//Реализация репозитория, который берет данные из памяти

class InMemoryEventRepository(
    private val api: EventsApi,
    private val ioDispatcher: IoDispatcher
) : EventRepository {

    private var events = listOf<DataEvent>()
        set(value) {
            if (field != value) {
                field = value
                listeners.forEach { it(value) }
            }
        }

    private val listeners = mutableSetOf<EventListener>()

    override fun addListener(listener: EventListener) {
        listeners += listener
        listener(events)
    }

    override fun removeListener(listener: EventListener) {
        listeners -= listener
    }

    override suspend fun getEvents(): List<DataEvent> = withContext(ioDispatcher.value) {
        delay(2000)
        events = api.getEvents()
        return@withContext events
    }

    override suspend fun getEventById(id: Long): DataEventDescription =
        withContext(ioDispatcher.value) {
            delay(2000)
            return@withContext api.getEventById(id)
        }

    // Тут происходит фильтрация данных и подготовка их для вьюмодели
}

