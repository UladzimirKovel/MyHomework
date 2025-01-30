package com.example.myhomework.presentation.view_model

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhomework.data.database.UserDatabase
import com.example.myhomework.data.model.UserEntity
import com.example.myhomework.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    context: Context,
    private val repository: UserRepository
) : ViewModel() {

//    private val userRepository: UserRepository? = null

    private val _registrUser = MutableLiveData<Boolean?>()
    val registrUser: LiveData<Boolean?> get() = _registrUser

    private val _registrLogin = MutableLiveData<UserEntity>()
    val registrLogin: LiveData<UserEntity?> get() = _registrLogin

    val db = UserDatabase.getDatabase(context)

    @SuppressLint("NullSafeMutableLiveData")
    fun registrationUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    )  {
        viewModelScope.launch(Dispatchers.IO) {

            val success = repository.registrUser(firstName, lastName, email, password)
            db.userDao().create(user = UserEntity(null, firstName, lastName, email, password))
            _registrUser.postValue(success)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val user = repository.loginUser(email, password)
            _registrUser.postValue(user != null)
        }
    }

    fun resetRegistrState(){
        _registrUser.postValue(null)
    }
}
