package com.example.tickettoshow.application.model.user.api

import com.example.tickettoshow.R
import com.example.tickettoshow.application.model.event.DataEvent
import com.example.tickettoshow.application.model.event.HistoryEvent

class InMemoryUsersApi : UsersApi {

    private val users = mutableListOf(
        DataUser(
            id = 1,
            name = "Oleg",
            email = "oleg@email.com",
            password = "123Gga$",
            token = "1"
        ),
        DataUser(
            id = 2,
            name = "Pasha",
            email = "pasha@email.com",
            password = "456Gga$",
            token = "2"
        ),
        DataUser(
            id = 3,
            name = "Misha",
            email = "Misha@email.com",
            password = "789Gga$",
            token = "3"
        ),
        DataUser(
            id = 4,
            name = "",
            email = "q",
            password = "1",
            token = "4"
        )
    )

    private var historyEvents = mutableListOf(
        HistoryEvent(
            id = 1,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            price = "400p",
            place = "3 ряд, 6 место",
            dateBought = "18 сентября",
            visited = true
        ),
        HistoryEvent(
            id = 1,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            price = "400p",
            place = "3 ряд, 6 место",
            dateBought = "18 сентября",
            visited = true
        ),
        HistoryEvent(
            id = 1,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            price = "400p",
            place = "3 ряд, 6 место",
            dateBought = "18 сентября",
            visited = true
        ),
        HistoryEvent(
            id = 1,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            price = "400p",
            place = "3 ряд, 6 место",
            dateBought = "18 сентября",
            visited = true
        ),
        HistoryEvent(
            id = 1,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            price = "400p",
            place = "3 ряд, 6 место",
            dateBought = "18 сентября",
            visited = true
        )
    )

    private var favoriteEvents = mutableListOf(
        DataEvent(
            id = 1,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            posterId = R.drawable.test_img
        ),
        DataEvent(
            id = 2,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            posterId = R.drawable.test_img
        ),
        DataEvent(
            id = 3,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            posterId = R.drawable.test_img
        ),
        DataEvent(
            id = 3,
            name = "Игорь Бутман",
            date = "19 сентября",
            time = "18:00",
            address = "Дом музыки",
            posterId = R.drawable.test_img
        ),
    )

    override fun checkEmailIsUnic(email: String): Boolean {
        return users.firstOrNull { it.email == email } == null
    }

    override fun registerUser(user: DataUser): DataUser {
        val newUser = user.copy(id = users.last().id + 1, token = users.last().id.toString())
        users.add(newUser)
        return newUser
    }

    override fun logInUser(email: String, password: String): Pair<String?, String> {
        val user = users.firstOrNull { it.email == email } ?: return Pair(null, "email_is_bad")
        return if (user.password == password)
            Pair(user.token, "good")
        else
            Pair(null, "password_is_bad")
    }

    override fun changeUserPassword(email: String, new_password: String) {
        val user = users.firstOrNull { it.email == email }!!
        updateUserData(user, password = new_password)
    }

    override fun updateUserData(user: DataUser, email: String?, name: String?, password: String?): String {
        val newUser = DataUser(
            id = user.id,
            email = email ?: user.email,
            name = name ?: user.name,
            password = password ?: user.password,
            token = user.id.toString()
        )
        users[(user.id - 1).toInt()] = newUser
        return newUser.token
    }

    override fun getHistory(userId: Long): List<HistoryEvent> {
        return historyEvents
    }

    override fun getUserByToken(token: String?): DataUser? {
        if (token == null)
            return null
        return users.firstOrNull { it.token == token }
    }

    override fun getFavorite(userId: Long): List<DataEvent> {
        return favoriteEvents
    }

    override fun addEventToFavorite(userId: Long, event: DataEvent): Boolean {
        favoriteEvents.add(event)
        return true
    }


}