package com.example.myhomework.presentation.view_model

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.myhomework.domain.use_case.isEmailValid


class LoginFragmentViewModel : ViewModel() {

    fun validateInput(
        email: EditText,
        password: EditText,
        context: Context
    ): Boolean {
        val emailString = email.text.toString().trim()
        val validationResPasswordSignup = password.text.toString().trim()

        return when {
            emailString.isEmpty() -> {
                Toast.makeText(context, "Email string is empty", Toast.LENGTH_LONG).show()
                false
            }

            validationResPasswordSignup.isEmpty() -> {
                Toast.makeText(context, "Password string is empty", Toast.LENGTH_LONG).show()
                false
            }

            !isEmailValid(emailString) -> {
                Toast.makeText(context, "Incorrect Email validation", Toast.LENGTH_LONG).show()
                false
            }

            password.length() !in 8..30 -> {
                Toast.makeText(
                    context,
                    "Password length should be from 8 to 30 symbols",
                    Toast.LENGTH_LONG
                ).show()
                false
            }

            else -> true
        }
    }
}