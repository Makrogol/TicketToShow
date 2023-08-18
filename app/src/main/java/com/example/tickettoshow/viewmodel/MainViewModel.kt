package com.example.tickettoshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tickettoshow.repository.MainRepository
import com.example.tickettoshow.model.DataEvent

class MainViewModel : ViewModel() {

    lateinit var repository: MainRepository

    private val _events = MutableLiveData<List<DataEvent>>()
    val events: LiveData<List<DataEvent>> = _events

    fun getEvents() = _events.postValue(repository.getEvents())


}