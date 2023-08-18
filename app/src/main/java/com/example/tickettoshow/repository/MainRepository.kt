package com.example.tickettoshow.repository

import com.example.tickettoshow.model.DataEvent
import com.example.tickettoshow.model.EventsApi

interface MainRepository {

    fun getEvents(): List<DataEvent>

}


class MainRepositoryImpl(
    private val api: EventsApi,
) : MainRepository {

    override fun getEvents() = api.getEvents()
    // Тут происходит фильтрация данных и подготовка их для вьюмодели

}