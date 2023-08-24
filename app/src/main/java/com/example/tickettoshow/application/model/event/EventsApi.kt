package com.example.tickettoshow.application.model.event

import com.example.tickettoshow.R
import java.lang.RuntimeException

interface EventsApi {

    fun getEvents(): List<DataEvent>

    fun getEventById(id: Long): DataEventDescription

}

class EventsApiImpl: EventsApi {

    private var events = mutableListOf<DataEvent>()

    override fun getEvents(): List<DataEvent> {
        // Тут должен быть завпрос в сеть или в бд
        events = listOf(
            DataEvent(
            id = 1,
            name = "name1",
            date = "date1",
            time = "time1",
            address = "address1",
            posterId = R.drawable.test_img
            ),
        DataEvent(
            id = 2,
            name = "name2",
            date = "date2",
            time = "time2",
            address = "address2",
            posterId = R.drawable.test_img
        ),
            DataEvent(
                id = 3,
                name = "name3",
                date = "date3",
                time = "time3",
                address = "address3",
                posterId = R.drawable.test_img
            ),
        ).toMutableList()
        return events
    }

    override fun getEventById(id: Long): DataEventDescription {
        val event = events.firstOrNull { it.id == id } ?: throw RuntimeException("api get user by id exception")
        return DataEventDescription(
            event = event,
            description = "Some Description"
        )
    }


}