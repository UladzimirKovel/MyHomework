package com.example.myhomework.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class UserSharedPref(context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("USER_PREFERENCES", Context.MODE_PRIVATE)

    fun saveUser(firstName : String, lastName: String, email : String, password : String) {

        sharedPref.edit(commit = true) {
            putString("firstName", firstName)
            putString("lastName", lastName)
            putString("email", email)
            putString("password", password)
        }

//        val editor = sharedPref.edit()
//        editor.putString("firstName", firstName)
//        editor.putString("lastName", lastName)
//        editor.putString("email", email)
//        editor.putString("password", password)
//        editor.apply()
    }

    fun getCurrentUser(): Pair<String, String>? {

        val email = sharedPref.getString("email", null)
        val password = sharedPref.getString("password", null)
        return if (email != null && password != null) Pair(email, password) else null
    }

    fun clearUser() {
        sharedPref.edit().clear().apply()
    }
}