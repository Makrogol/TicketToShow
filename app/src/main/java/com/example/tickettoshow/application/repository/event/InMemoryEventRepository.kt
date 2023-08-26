package com.example.tickettoshow.application.repository.event

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.EventsApi
import com.example.tickettoshow.foundation.model.tasks.factories.TasksFactory
import com.example.tickettoshow.foundation.model.tasks.ThreadUtils

//Реализация репозитория, который берет данные из памяти

class InMemoryEventRepository(
    private val api: EventsApi,
    private val tasksFactory: TasksFactory,
    private val threadUtils: ThreadUtils,
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

    override fun getEvents() = tasksFactory.async {
        threadUtils.sleep(2000)
        events = api.getEvents()
        return@async events
    }

    override fun getEventById(id: Long) = tasksFactory.async{
        threadUtils.sleep(2000)
        return@async api.getEventById(id)
    }

    // Тут происходит фильтрация данных и подготовка их для вьюмодели
}

