@file:Suppress("DEPRECATION")

package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signupButtonMain: Button = findViewById(R.id.button_signup)
        val accTextViewSignup: TextView = findViewById(R.id.main_textview_to_login)

        val signupTextViewFirstname: EditText = findViewById(R.id.signup_textview_firstname)
        val signupTextViewLastname: EditText = findViewById(R.id.signup_textview_lastname)
        val signupTextViewEmail: EditText = findViewById(R.id.email)
        val signupTextViewPassword: EditText = findViewById(R.id.password)
        val emailString = signupTextViewEmail.text.toString()

        signupTextViewFirstname.doAfterTextChanged { text ->
            when (val validationResFirstName: ValidResult = isNameValid(text.toString())) {
                is ValidResult.Valid -> {
                    signupTextViewFirstname.error = null
                }

                is ValidResult.Invalid -> {
                    signupTextViewFirstname.error = getString(validationResFirstName.errorRes)
                }
            }
        }

        signupTextViewLastname.doAfterTextChanged { text ->
            when (val validationResLastName: ValidResult = isNameValid(text.toString())) {
                is ValidResult.Valid -> {
                    signupTextViewLastname.error = null
                }

                is ValidResult.Invalid -> {
                    signupTextViewLastname.error = getString(validationResLastName.errorRes)
                }
            }
        }

        signupTextViewEmail.doAfterTextChanged {
            if (isEmailValid(emailString)) {
                Toast.makeText(this, "Valid Email", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error Email", Toast.LENGTH_LONG).show()
            }
        }

        signupTextViewPassword.doAfterTextChanged { text ->
            when (val validationResPasswordSignup: ValidResult = isValidPassword(text.toString())) {
                is ValidResult.Valid -> {
                    signupTextViewPassword.error = null
                }

                is ValidResult.Invalid -> {
                    signupTextViewPassword.error = getString(validationResPasswordSignup.errorRes)
                }
            }

        }

        signupButtonMain.setOnClickListener {
            onBackPressed()
        }

        accTextViewSignup.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }
}