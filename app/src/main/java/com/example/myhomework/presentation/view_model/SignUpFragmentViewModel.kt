package com.example.myhomework.presentation.view_model

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.myhomework.domain.use_case.isEmailValid

class SignUpFragmentViewModel : ViewModel() {

    fun validateInput(
        firstName: EditText,
        lastName: EditText,
        email: EditText,
        password: EditText,
        context: Context
    ): Boolean {

        val isNameValid = firstName.text.toString().trim()
        val isLastNameValid = lastName.text.toString().trim()
        val emailString = email.text.toString().trim()
        val isValidPassword = password.text.toString().trim()

        return when {
            emailString.isEmpty()  -> {
                Toast.makeText(context, "Email string is empty", Toast.LENGTH_LONG).show()
                false
            }

            isValidPassword.isEmpty() -> {
                Toast.makeText(context, "Password string is empty", Toast.LENGTH_LONG).show()
                false
            }

            isNameValid.isEmpty() -> {
                Toast.makeText(context, "First Name string is empty", Toast.LENGTH_LONG).show()
                false
            }

            isLastNameValid.isEmpty() -> {
                Toast.makeText(context, "Last Nave string is empty", Toast.LENGTH_LONG).show()
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
                )
                    .show()
                false
            }

            firstName.length() !in 3..25 -> {
                Toast.makeText(
                    context,
                    "First Name length should be from 3 to 25 symbols ",
                    Toast.LENGTH_LONG
                )
                    .show()
                false
            }

            lastName.length() !in 3..25 -> {
                Toast.makeText(
                    context,
                    "Last Name length should be from 3 to 25 symbols",
                    Toast.LENGTH_LONG
                )
                    .show()
                false
            }

            else -> true
        }
    }
}