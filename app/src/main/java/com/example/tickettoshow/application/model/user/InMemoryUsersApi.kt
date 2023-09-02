package com.example.tickettoshow.application.model.user

class InMemoryUsersApi : UsersApi {

    private val users = mutableListOf<DataUser>(
        DataUser(
            id = 1,
            name = "Oleg",
            email = "oleg@email.com",
            password = "123Gga$"
        ),
        DataUser(
            id = 2,
            name = "Pasha",
            email = "pasha@email.com",
            password = "456Gga$"
        ),
        DataUser(
            id = 3,
            name = "Misha",
            email = "Misha@email.com",
            password = "789Gga$"
        )
    )

    override fun checkUserByEmail(email: String): Boolean {
        return users.firstOrNull { it.email == email } == null
    }

    override fun registerUser(user: DataUser) {
        val newUser = user.copy(id = users.last().id + 1)
        users.add(newUser)
    }

}