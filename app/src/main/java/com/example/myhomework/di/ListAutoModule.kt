package com.example.myhomework.di

import com.example.myhomework.domain.repository.ListAutoRepository
import org.koin.dsl.module

val listAutoModule = module{
    single{ListAutoRepository}
}