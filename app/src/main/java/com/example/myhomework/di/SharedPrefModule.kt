package com.example.myhomework.di

import com.example.myhomework.data.repository.UserSharedPref
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val sharedPrefModule = module {
    factoryOf(::UserSharedPref)
}