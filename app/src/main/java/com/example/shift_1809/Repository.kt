package com.example.shift_1809

import com.example.shift_1809.Model.User
import java.util.Date

object Repository {
    private lateinit var user: User

    fun createUser(name: String, surName: String, password: String, date: Date) {
        user = User(name, surName, date, password)
    }

    fun getUserName() = user.name
    fun getUserDate() = user.birthday.toString()
    fun getUserSurname() = user.surname
    fun getUserPassword() = user.password


}