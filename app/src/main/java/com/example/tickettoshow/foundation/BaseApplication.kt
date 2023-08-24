package com.example.tickettoshow.foundation

import com.example.tickettoshow.foundation.model.Repository

interface BaseApplication {

    val repositories: List<Repository>

}