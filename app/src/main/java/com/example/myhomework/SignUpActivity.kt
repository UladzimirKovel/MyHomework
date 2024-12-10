package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private var signupButtonMain: Button? = null
    private var accTextViewSignup: TextView? = null
    private var signupTextViewFirstname: EditText? = null
    private var signupTextViewLastname: EditText? = null
    private var signupTextViewEmail: EditText? = null
    private var signupTextViewPassword: EditText? = null
    private var buttonReg: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initialize()
        initClick()
    }

    private fun validation(
        signupTextViewFirstname: EditText,
        signupTextViewLastname: EditText,
        signupTextViewEmail: EditText,
        signupTextViewPassword: EditText
    ) {
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

    private fun initClick() {

        buttonReg?.setOnClickListener {
            validation(signupTextViewFirstname!!,
                signupTextViewLastname!!,
                signupTextViewEmail!!,
                signupTextViewPassword!!)
        }
        signupButtonMain?.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
        accTextViewSignup?.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }

    private fun initialize() {

        signupButtonMain = findViewById(R.id.button_signup)
        accTextViewSignup = findViewById(R.id.main_textview_to_login)
        signupTextViewFirstname = findViewById(R.id.signup_textview_firstname)
        signupTextViewLastname = findViewById(R.id.signup_textview_lastname)
        signupTextViewEmail = findViewById(R.id.email)
        signupTextViewPassword = findViewById(R.id.password)
        buttonReg = findViewById(R.id.login_button)
    }
}