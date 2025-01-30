package com.example.myhomework.domain.repository

import com.example.myhomework.data.model.UserEntity

interface UserRepository {

    suspend fun registrUser(firstName:String, lastName:String, email:String, password:String): Boolean?

    suspend fun loginUser(email: String, password: String): UserEntity?

    suspend fun getCurrentUser(): UserEntity?
}