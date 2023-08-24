package com.example.tickettoshow.application.repository.event

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.DataEventDescription
import com.example.tickettoshow.application.model.event.EventsApi
import com.example.tickettoshow.application.tasks.SimpleTask
import java.util.concurrent.Callable


class InMemoryEventRepository(
    private val api: EventsApi,
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

    override fun getEvents() = SimpleTask<List<DataEvent>>(Callable {
        Thread.sleep(2000)
        events = api.getEvents()
        return@Callable events
    })

    override fun getEventById(id: Long) = SimpleTask<DataEventDescription>(Callable {
        Thread.sleep(2000)
        return@Callable api.getEventById(id)
    })

    // Тут происходит фильтрация данных и подготовка их для вьюмодели
}

