package com.example.tickettoshow.application

import android.app.Application
import com.example.tickettoshow.application.model.event.EventsApiImpl
import com.example.tickettoshow.application.repository.event.InMemoryEventRepository
import com.example.tickettoshow.foundation.model.tasks.factories.ThreadTasksFactory
import com.example.tickettoshow.foundation.BaseApplication
import com.example.tickettoshow.foundation.model.tasks.ThreadUtils
import com.example.tickettoshow.foundation.model.tasks.dispatchers.MainThreadDispatcher
import com.example.tickettoshow.foundation.model.tasks.factories.ExecutorServiceTasksFactory
import java.util.concurrent.Executors


//Singltone Scope

class App : Application(), BaseApplication {

    private val threadUtils = ThreadUtils.Default()
    private val dispatcher = MainThreadDispatcher()
//    private val tasksFactory = ThreadTasksFactory()
    private val tasksFactory = ExecutorServiceTasksFactory(Executors.newCachedThreadPool())
    private val repository = InMemoryEventRepository(EventsApiImpl(), tasksFactory, threadUtils)

    override val singletonScopeDependencies = listOf(
        tasksFactory,
        dispatcher,
        repository
    )

}