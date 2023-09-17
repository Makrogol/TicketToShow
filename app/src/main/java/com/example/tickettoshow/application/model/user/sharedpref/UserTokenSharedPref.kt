package com.example.tickettoshow.application.model.user.sharedpref

interface UserTokenSharedPref {

    fun putToken(userToken: String)

    fun getToken(): String?

    fun clearToken()

}