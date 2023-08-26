package com.example.tickettoshow.foundation.model.tasks

import android.os.Handler
import android.os.Looper
import com.example.tickettoshow.foundation.model.tasks.dispatchers.Dispatcher
import java.lang.Exception

// Собственная реализация асинхронной задачи


typealias TaskListener<T> = (FinalResult<T>) -> Unit


class CancelledException(originException: Exception? = null) : Exception(originException)


interface Task<T> {

    fun enqueue(dispatcher: Dispatcher, listener: TaskListener<T>)

    fun cancel()

    fun await(): T
}