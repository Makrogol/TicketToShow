package com.example.tickettoshow.repository

import com.example.tickettoshow.model.EventsApi
import com.example.tickettoshow.model.EventsApiImpl

interface DependencyProvider {

    fun provideApi(): EventsApi

    fun provideMainRepository(): MainRepository

}


class DependencyProviderImpl : DependencyProvider {

    override fun provideApi(): EventsApi = EventsApiImpl()

    override fun provideMainRepository(): MainRepository = MainRepositoryImpl(provideApi())

}