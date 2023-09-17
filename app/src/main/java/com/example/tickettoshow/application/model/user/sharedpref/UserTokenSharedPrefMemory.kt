package com.example.tickettoshow.application.model.user.sharedpref

import android.content.Context
import android.content.SharedPreferences

class UserTokenSharedPrefMemory(
    context: Context
): UserTokenSharedPref {

    private val settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    private val editor = settings.edit()

    override fun putToken(userToken: String) {
        editor.putString(TOKEN_NAME, userToken).apply()
    }

    override fun getToken() = settings.getString(TOKEN_NAME, null)

    override fun clearToken() {
        //Тут надо сделать так, чтобы удалялся токен, чтобы при дальнейшем вызове getToken мне возвращало null
        editor.clear()
    }

    companion object {
        private val STORAGE_NAME = "userToken"
        private val TOKEN_NAME = "token"
    }
}