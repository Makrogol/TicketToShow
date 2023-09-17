package com.example.tickettoshow.application.repository.user

import com.example.tickettoshow.application.model.user.api.DataUser
import com.example.tickettoshow.application.model.user.api.UsersApi
import com.example.tickettoshow.foundation.model.coroutines.IoDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class InMemoryUserRepository(
    private val api: UsersApi,
    private val ioDispatcher: IoDispatcher
) : UserRepository {

    private val listeners = mutableSetOf<UserListener>()
    private var users = listOf<DataUser>()
        set(value) {
            if (value != field) {
                field = value
                listeners.forEach { it(value) }
            }
        }


    override fun addListener(listener: UserListener) {
        listeners.add(listener)
        listener(users)
    }

    override fun removeListener(listener: UserListener) {
        listeners.remove(listener)
    }

    override suspend fun checkUserByEmail(email: String): Boolean = withContext(ioDispatcher.value) {
        delay(100)
        return@withContext api.checkUserByEmail(email)
    }

    override suspend fun registerUser(user: DataUser) = withContext(ioDispatcher.value) {
        delay(1000)
        return@withContext api.registerUser(user)
    }
}