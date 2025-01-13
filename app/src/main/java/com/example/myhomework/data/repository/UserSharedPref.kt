package com.example.myhomework.data.repository

import android.content.Context
import android.content.SharedPreferences

class UserSharedPref(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("UserPref", Context.MODE_PRIVATE)

    fun saveUser(firstName : String, lastName : String, email : String, password : String) {
        val editor = sharedPref.edit()
        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    fun getUser() : Map<String, String?> { // Придумать использование
        return mapOf(
            "firstName" to sharedPref.getString("firstName", null),
            "lastName" to sharedPref.getString("lastName", null),
            "email" to sharedPref.getString("email", null),
            "password" to sharedPref.getString("password", null)
        )
    }
}