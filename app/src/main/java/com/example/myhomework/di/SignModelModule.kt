package com.example.myhomework.di

import com.example.myhomework.presentation.view_model.SignUpFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val signUpModelModule = module{
    viewModelOf(::SignUpFragmentViewModel)
}