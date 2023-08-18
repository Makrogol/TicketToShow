package com.example.tickettoshow.model

import java.io.Serializable

data class DataEvent(
    val name: String,
    val description: String,
    val address: String,
    val time: String,
    val date: String,
    val posterId: Int //Картинку надо подумать, как вставлять
) : Serializable
