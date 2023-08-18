package com.example.tickettoshow.model

import com.example.tickettoshow.R

interface EventsApi {

    fun getEvents(): List<DataEvent>

}

class EventsApiImpl: EventsApi {
    override fun getEvents(): List<DataEvent> {
        // Тут должен быть завпрос в сеть или в бд
        return listOf(
            DataEvent(
            name = "name1",
            description = "descr1",
            date = "date1",
            time = "time1",
            address = "address1",
            posterId = R.drawable.test_img
            ),
        DataEvent(
            name = "name2",
            description = "descr2",
            date = "date2",
            time = "time2",
            address = "address2",
            posterId = R.drawable.test_img
        ),
            DataEvent(
                name = "name3",
                description = "descr3",
                date = "date3",
                time = "time3",
                address = "address3",
                posterId = R.drawable.test_img
            ),
        )
    }


}