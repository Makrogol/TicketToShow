package com.example.tickettoshow.foundation.model.tasks.factories

import com.example.tickettoshow.foundation.model.tasks.Task


typealias TaskBody<T> = () -> T


interface TasksFactory {

    fun <T> async(body: TaskBody<T>): Task<T>

}