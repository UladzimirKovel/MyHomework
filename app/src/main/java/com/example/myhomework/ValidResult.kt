package com.example.myhomework

import android.text.TextUtils

fun isEmailValid(emailString: String): Boolean {
    return !TextUtils.isEmpty(emailString) &&
        android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()
}

