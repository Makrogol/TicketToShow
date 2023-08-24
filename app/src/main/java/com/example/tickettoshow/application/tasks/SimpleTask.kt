package com.example.tickettoshow.application.tasks

import com.example.tickettoshow.foundation.tasks.Callback
import com.example.tickettoshow.foundation.tasks.ErrorResult
import com.example.tickettoshow.foundation.tasks.PendingResult
import com.example.tickettoshow.foundation.tasks.Result
import com.example.tickettoshow.foundation.tasks.SuccessResult
import com.example.tickettoshow.foundation.tasks.Task
import java.lang.Exception
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

private val executorService = Executors.newCachedThreadPool()
//private val handler = Handler(Looper.getMainLooper())

class SimpleTask<T>(
    private val callable: Callable<T>
) : Task<T> {

    private val future: Future<*>
    private var result: Result<T> = PendingResult()

    init {
        future = executorService.submit {
            result = try {
                SuccessResult(callable.call())
            } catch (e: Exception) {
                ErrorResult(e)
            }
            notifyListeners()
        }
    }

    private var valueCallback: Callback<T>? = null
    private var errorCallback: Callback<Exception>? = null

    override fun onSuccess(callback: Callback<T>): Task<T> {
        this.valueCallback = callback
        notifyListeners()
        return this
    }

    override fun onError(callback: Callback<Exception>): Task<T> {
        this.errorCallback = callback
        notifyListeners()
        return this
    }

    override fun cancel() {
        clear()
        future.cancel(true)
    }

    override fun await(): T {
        future.get()
        val result = this.result
        if (result is SuccessResult) return result.data
        else throw (result as ErrorResult).exception
    }

    private fun notifyListeners() {
        val result = this.result
        val callback = this.valueCallback
        val errorCallback = this.errorCallback

        if (result is SuccessResult && callback != null) {
            callback(result.data)
            clear()
        } else if (result is ErrorResult && errorCallback != null) {
            errorCallback.invoke(result.exception)
            clear()
        }
    }

    private fun clear() {
        valueCallback = null
        errorCallback = null
    }

}