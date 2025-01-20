package com.example.myhomework.di

import com.example.myhomework.presentation.view_model.ListViewAutoModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val listAutoModelModule = module {
    viewModelOf(::ListViewAutoModel)
}