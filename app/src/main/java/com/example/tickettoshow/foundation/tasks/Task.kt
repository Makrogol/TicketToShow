package com.example.tickettoshow.foundation.tasks

import java.lang.Exception


typealias Callback<T> = (T) -> Unit


interface Task<T> {

    fun onSuccess(callback: Callback<T>): Task<T>

    fun onError(callback: Callback<Exception>): Task<T>

    fun cancel()

    fun await(): T
}