package com.example.tickettoshow.application

import android.app.Application
import com.example.tickettoshow.application.model.event.EventsApiImpl
import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.model.user.api.InMemoryUsersApi
import com.example.tickettoshow.application.model.user.sharedpref.UserTokenSharedPrefMemory
import com.example.tickettoshow.application.repository.event.InMemoryEventRepository
import com.example.tickettoshow.application.repository.user.InMemoryUserRepository
import com.example.tickettoshow.foundation.BaseApplication
import com.example.tickettoshow.foundation.model.coroutines.IoDispatcher
import com.example.tickettoshow.foundation.model.coroutines.WorkerDispatcher
import kotlinx.coroutines.Dispatchers


//Singltone Scope

class App : Application(), BaseApplication {


    private val ioDispatcher = IoDispatcher(Dispatchers.IO)
    private val workerDispatcher = WorkerDispatcher(Dispatchers.Default)
    private val eventRepository = InMemoryEventRepository(EventsApiImpl(), ioDispatcher)
    private val userRepository = InMemoryUserRepository(InMemoryUsersApi(), ioDispatcher)
    private val sharedPref = UserTokenSharedPrefMemory(Application().applicationContext)

    var currentUser: DataUser? = null

    override val singletonScopeDependencies = listOf(
        eventRepository,
        userRepository,
        sharedPref
    )

}