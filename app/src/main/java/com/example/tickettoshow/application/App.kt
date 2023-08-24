package com.example.tickettoshow.application

import android.app.Application
import com.example.tickettoshow.application.repository.event.DependencyProvider
import com.example.tickettoshow.application.repository.event.DependencyProviderImpl
import com.example.tickettoshow.foundation.BaseApplication


//Singltone Scope

class App : Application(), BaseApplication {

    private val provider: DependencyProvider = DependencyProviderImpl()
    private val repository = provider.provideMainRepository()

    override val repositories = listOf(
        repository
    )

}