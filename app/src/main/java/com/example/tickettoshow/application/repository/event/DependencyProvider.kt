package com.example.tickettoshow.application.repository.event

import com.example.tickettoshow.application.model.event.EventsApi
import com.example.tickettoshow.application.model.event.EventsApiImpl
import com.example.tickettoshow.foundation.model.Repository

interface DependencyProvider {

    fun provideApi(): EventsApi

    fun provideMainRepository(): Repository

}


class DependencyProviderImpl : DependencyProvider {

    override fun provideApi(): EventsApi = EventsApiImpl()

    override fun provideMainRepository(): Repository = InMemoryEventRepository(provideApi())

}