package com.example.myhomework.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhomework.data.api.ApiExample
import com.example.myhomework.data.repository.RetrofitImpl
import com.example.myhomework.presentation.action.MainFragmentActions

class MainFragmentViewModel : ViewModel() {

    private val _liveData = MutableLiveData(CurrentState())
    val liveData: LiveData<CurrentState> get() = _liveData

    private val rt: RetrofitImpl = RetrofitImpl()
    val api = rt.retrofit.create(ApiExample::class.java)

    fun handleAction(action: MainFragmentActions) {
        when (action) {
            MainFragmentActions.GoToListAutoFragment -> _liveData.value =
                CurrentState(buttonAuto = true)

            MainFragmentActions.GoToLoginFragment -> _liveData.value =
                CurrentState(
                    buttonAuto = false,
                    buttonSignUp = false,
                    buttonLogin = true,
                )

            MainFragmentActions.GoToSignUpFragment -> _liveData.value =
                CurrentState(
                    buttonAuto = false,
                    buttonSignUp = true,
                    buttonLogin = false,
                )
        }
    }

    data class CurrentState(
        val buttonAuto: Boolean = false,
        val buttonSignUp: Boolean = false,
        val buttonLogin: Boolean = false,
    )

    suspend fun apiResponce() {
        val apiAllCharacters = api.getAllCharacters()
        Log.e("AllObjects", "$apiAllCharacters")

        val apiOneCharacter = api.getOneCharacter(308)
        Log.e("OneObject", "$apiOneCharacter")

        val apiFilterCharacters = api.getFilterCharacters()
        Log.e("Filter", "$apiFilterCharacters")
    }
}