package com.example.tickettoshow.application.repository.user

import com.example.tickettoshow.application.model.event.DataEvent
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

    override suspend fun checkEmailIsUnic(email: String): Boolean =
        withContext(ioDispatcher.value) {
            delay(100)
            return@withContext api.checkEmailIsUnic(email)
        }

    override suspend fun registerUser(user: DataUser) = withContext(ioDispatcher.value) {
        delay(1000)
        return@withContext api.registerUser(user)
    }

    override suspend fun getUserByToken(token: String?) = withContext(ioDispatcher.value) {
        delay(1000)
        return@withContext api.getUserByToken(token)
    }

    override suspend fun getHistory(userId: Long) = withContext(ioDispatcher.value) {
        delay(1000)
        return@withContext api.getHistory(userId)
    }

    override suspend fun logInUser(email: String, password: String): Pair<String?, String> =
        withContext(ioDispatcher.value) {
            delay(1000)
            return@withContext api.logInUser(email, password)
        }

    override suspend fun changePassword(email: String, new_password: String) =
        withContext(ioDispatcher.value) {
            delay(1000)
            api.changeUserPassword(email, new_password)
        }

    override suspend fun getFavorite(userId: Long) = withContext(ioDispatcher.value) {
        delay(1000)
        return@withContext api.getFavorite(userId)
    }

    override suspend fun addEventToFavorite(userId: Long, event: DataEvent) =
        withContext(ioDispatcher.value) {
            delay(100)
            return@withContext api.addEventToFavorite(userId, event)
        }


    override suspend fun updateUserData(
        user: DataUser,
        email: String?,
        name: String?,
        password: String?
    ) = withContext(ioDispatcher.value) {
        delay(1000)
        api.updateUserData(user, email, name, password)
    }
}