package com.example.myhomework.domain.use_case

import android.text.TextUtils

fun isEmailValid(emailString: String): Boolean {
    return !TextUtils.isEmpty(emailString) &&
        android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()
}

object Constants {
    const val EMPTY_STRING = ""
}
