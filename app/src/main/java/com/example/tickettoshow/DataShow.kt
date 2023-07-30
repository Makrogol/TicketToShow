package com.example.tickettoshow

import android.graphics.drawable.Drawable

data class DataShow(
    val name: String,
    val description: String,
    val address: String,
    val time: String,
    val date: String,
    val poster: Drawable? //Картинку надо подумать, как вставлять
){}
