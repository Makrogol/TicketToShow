package com.example.tickettoshow.application.repository.user

import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.HistoryEvent
import com.example.tickettoshow.application.model.user.api.DataUser

typealias UserListener = (List<DataUser>) -> Unit


interface UserRepository {

    fun addListener(listener: UserListener)

    fun removeListener(listener: UserListener)

    suspend fun checkEmailIsUnic(email: String): Boolean

    suspend fun getHistory(userId: Long): List<HistoryEvent>

    suspend fun registerUser(user: DataUser): DataUser

    suspend fun getUserByToken(token: String?): DataUser?

    suspend fun logInUser(email: String, password: String): Pair<String?, String>

    suspend fun changePassword(email: String, new_password: String)

    suspend fun getFavorite(userId: Long): List<DataEvent>

    suspend fun addEventToFavorite(userId: Long, event: DataEvent): Boolean

    suspend fun updateUserData(
        user: DataUser,
        email: String? = null,
        name: String? = null,
        password: String? = null
    ): String
}