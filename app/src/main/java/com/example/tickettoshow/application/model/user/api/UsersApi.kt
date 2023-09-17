package com.example.tickettoshow.application.model.user.api

interface UsersApi {

    fun checkUserByEmail(email: String): Boolean

    fun registerUser(user: DataUser): DataUser

}