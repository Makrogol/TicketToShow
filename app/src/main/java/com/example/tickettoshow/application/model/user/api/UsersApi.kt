package com.example.tickettoshow.application.model.user.api

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.HistoryEvent

interface UsersApi {

    fun checkEmailIsUnic(email: String): Boolean

    fun registerUser(user: DataUser): DataUser

    fun logInUser(email: String, password: String): Pair<String?, String>

    fun changeUserPassword(email: String, new_password: String)

    fun getHistory(userId: Long): List<HistoryEvent>

    fun getFavorite(userId: Long): List<DataEvent>

    fun getUserByToken(token: String?): DataUser?

    fun addEventToFavorite(userId: Long, event: DataEvent): Boolean

    fun updateUserData(
        user: DataUser,
        email: String? = null,
        name: String? = null,
        password: String? = null
    ): String
}