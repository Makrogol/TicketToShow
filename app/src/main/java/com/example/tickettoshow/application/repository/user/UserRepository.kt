package com.example.tickettoshow.application.repository.user

import com.example.tickettoshow.application.model.user.DataUser

typealias UserListener = (List<DataUser>) -> Unit



interface UserRepository {

    fun addListener(listener: UserListener)

    fun removeListener(listener: UserListener)

    suspend fun checkUserByEmail(email: String): Boolean

    suspend fun registerUser(user: DataUser)

}