package com.example.tickettoshow.foundation.model.dispatchers

import android.os.Handler
import android.os.Looper


// Переводит переданный блок кода в главный поток, если он там не был. Иначе запускает его мгновенно

class MainThreadDispatcher : Dispatcher {

    private val handler = Handler(Looper.getMainLooper())

    override fun dispatch(block: () -> Unit) {
        if (Looper.getMainLooper().thread.id == Thread.currentThread().id){
            block()
        } else {
            handler.post { block() }
        }
    }


}