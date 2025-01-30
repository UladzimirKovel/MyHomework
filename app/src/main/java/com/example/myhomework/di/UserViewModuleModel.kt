package com.example.myhomework.di

import com.example.myhomework.presentation.view_model.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val userModel = module {
    viewModelOf(::UserViewModel)
}