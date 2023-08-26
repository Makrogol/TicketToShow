package com.example.tickettoshow.foundation.model.tasks

//Интерфейс для взаимодействия с потоками

interface ThreadUtils {

    fun sleep(millis: Long)

    class Default : ThreadUtils {
        override fun sleep(millis: Long) {
            Thread.sleep(millis)
        }
    }
}
