package com.example.tickettoshow.application.model.event

import java.io.Serializable

data class DataEvent(
    val id: Long,
    val name: String,
    val address: String,
    val time: String,
    val date: String,
    val posterId: Int
) : Serializable

data class DataEventDescription(
    val event: DataEvent,
    val description: String
)

fun DataEventDescription.toDataEvent(): DataEvent {
    return this.event
}

data class HistoryEvent(
    val id: Long,
    val name: String,
    val address: String,
    val time: String,
    val date: String,
    val price: String,
    val place: String,
    val dateBought: String,
    val visited: Boolean
)