package com.example.myhomework.di

import com.example.myhomework.presentation.view_model.LoginFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginModelModule = module {
    viewModelOf(::LoginFragmentViewModel)
}