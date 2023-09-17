package com.example.tickettoshow.application.model.event

import com.example.tickettoshow.R
import java.lang.RuntimeException

interface EventsApi {

    fun getEvents(): List<DataEvent>

    fun getEventById(id: Long): DataEventDescription

}

class EventsApiImpl : EventsApi {

    private var events = mutableListOf<DataEvent>()


    override fun getEvents(): List<DataEvent> {
        // Тут должен быть завпрос в сеть или в бд
        events = listOf(
            DataEvent(
                id = 1,
                name = "Игорь Бутман",
                date = "19 сентября",
                time = "18:00",
                address = "Дом музыки",
                posterId = R.drawable.test_img
            ),
            DataEvent(
                id = 2,
                name = "Игорь Бутман",
                date = "19 сентября",
                time = "18:00",
                address = "Дом музыки",
                posterId = R.drawable.test_img
            ),
            DataEvent(
                id = 3,
                name = "Игорь Бутман",
                date = "19 сентября",
                time = "18:00",
                address = "Дом музыки",
                posterId = R.drawable.test_img
            ),
            DataEvent(
                id = 3,
                name = "Игорь Бутман",
                date = "19 сентября",
                time = "18:00",
                address = "Дом музыки",
                posterId = R.drawable.test_img
            ),
        ).toMutableList()
        return events
    }




    override fun getEventById(id: Long): DataEventDescription {
        val event = events.firstOrNull { it.id == id }
            ?: throw RuntimeException("api get user by id exception")
        return DataEventDescription(
            event = event,
            description = "Марко Маркович начал играть на трубе еще в детском саду. А сегодня он " +
                    "лидер собственного коллектива Big Brass Band, успешный представитель духовой" +
                    " музыки: трубач и флюгельгорнист, преемник своего отца, известного трубача " +
                    "Бобана Марковича, харизматичный исполнитель, композитор и аранжировщик. Его " +
                    "музыка наполнена диким вихрем зажигательных цыгано-балканских мелодий."
        )
    }


}