package com.example.myhomework.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhomework.presentation.action.MainFragmentActions

class MyViewModel : ViewModel() {

    private val _liveData = MutableLiveData(CurrentState())
    val liveData: LiveData<CurrentState> get() = _liveData

    fun handleAction(action: MainFragmentActions) {
        when (action) {
            MainFragmentActions.GoToListAutoFragment -> _liveData.value =
                CurrentState(buttonAuto = true)

            MainFragmentActions.GoToLoginFragment -> _liveData.value =
                CurrentState(buttonAuto = false, buttonSignUp = false, buttonLogin = true)

            MainFragmentActions.GoToSignUpFragment -> _liveData.value =
                CurrentState(buttonAuto = false, buttonSignUp = true, buttonLogin = false)
        }
    }

    data class CurrentState(
        val buttonAuto: Boolean = false,
        val buttonSignUp: Boolean = false,
        val buttonLogin: Boolean = false
    )
}