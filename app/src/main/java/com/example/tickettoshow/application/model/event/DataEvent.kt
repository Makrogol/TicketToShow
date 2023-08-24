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