package com.example.myhomework.data.repository

import com.example.myhomework.data.database.UserDatabase
import com.example.myhomework.data.model.UserEntity
import com.example.myhomework.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDatabase: UserDatabase,
    private val sharedPref: UserSharedPref
) : UserRepository {
    override suspend fun registrUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean {
        val existingUser = userDatabase.userDao().getUserByEmail(email)
        return if (existingUser == null) {
            val user = UserEntity(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password
            )
            userDatabase.userDao().create(user)
            sharedPref.saveUser(firstName, lastName, email, password)
            true
        } else false
    }

    override suspend fun loginUser(email: String, password: String): UserEntity? {
        return userDatabase.userDao().getUser(email, password)

    }

    override suspend fun getCurrentUser(): UserEntity? {
        val user = sharedPref.getCurrentUser()
        return if (user != null) {
            userDatabase.userDao().getUser(user.first, user.second)
        } else null
    }
}