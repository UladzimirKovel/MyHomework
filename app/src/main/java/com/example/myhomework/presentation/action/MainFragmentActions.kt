package com.example.myhomework.presentation.action

sealed interface MainFragmentActions {

    data object GoToListAutoFragment : MainFragmentActions

    data object GoToSignUpFragment : MainFragmentActions

    data object GoToLoginFragment : MainFragmentActions
}