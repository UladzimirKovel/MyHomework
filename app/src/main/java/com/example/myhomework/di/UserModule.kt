package com.example.myhomework.di

import com.example.myhomework.data.database.UserDatabase
import com.example.myhomework.data.repository.UserRepositoryImpl
import com.example.myhomework.domain.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule = module {
    single {
        UserDatabase.getDatabase(get ())
    }
    singleOf(::UserRepositoryImpl).bind(UserRepository::class)
}