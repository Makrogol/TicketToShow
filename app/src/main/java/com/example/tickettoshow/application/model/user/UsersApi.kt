package com.example.tickettoshow.application.model.user

interface UsersApi {

    fun checkUserByEmail(email: String): Boolean

    fun registerUser(user: DataUser)

}