package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
        val buttonReg: Button = findViewById(R.id.login_button)

        buttonReg.setOnClickListener {
            val isNameValid = signupTextViewFirstname.text.toString().trim()
            val isLastNameValid = signupTextViewLastname.text.toString().trim()
            val emailString = signupTextViewEmail.text.toString().trim()
            val isValidPassword = signupTextViewPassword.text.toString().trim()

            when {
                emailString == Constants.EMPTY_STRING ||
                        isLastNameValid == Constants.EMPTY_STRING ||
                        isNameValid == Constants.EMPTY_STRING ||
                        isValidPassword == Constants.EMPTY_STRING -> {
                    Toast.makeText(
                        this, "Not all fields are filled in", Toast.LENGTH_SHORT
                    ).show()
                }

                !isEmailValid(emailString) -> {
                    Toast.makeText(
                        this, "Error Email", Toast.LENGTH_LONG
                    ).show()
                }

                signupTextViewPassword.length() < 8 ||
                        signupTextViewFirstname.length() < 8 ||
                        signupTextViewLastname.length() < 8 -> {
                    Toast.makeText(
                        this, "Length should be min 8", Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

        }
        signupButtonMain.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
        accTextViewSignup.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }
}
