package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var loginButtonMain: Button? = null
    private var accTextviewLogin: TextView? = null
    private var buttonReg: Button? = null
    private var loginTextviewEmail: EditText? = null
    private var loginTextviewPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()

        buttonReg?.setOnClickListener {
            handleRegistration()
        }
        loginButtonMain?.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        accTextviewLogin?.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }

    private fun handleRegistration() {

        val emailString = loginTextviewEmail?.text.toString().trim()
        val validationResPasswordSignup = loginTextviewPassword?.text.toString().trim()

        when {
            emailString == Constants.EMPTY_STRING ||
                    validationResPasswordSignup == Constants.EMPTY_STRING -> {
                Toast.makeText(this, "not all fields are filled in", Toast.LENGTH_LONG)
                    .show()
            }

            !isEmailValid(emailString) -> {
                Toast.makeText(this, "Error Email", Toast.LENGTH_LONG).show()
            }

            validationResPasswordSignup.length < 8 || validationResPasswordSignup.length > 255 -> {
                Toast.makeText(this, "Length should be min 8", Toast.LENGTH_LONG).show()
            }

            else -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initialize() {
        loginButtonMain = findViewById(R.id.button_main)
        accTextviewLogin = findViewById(R.id.main_textview_to_login)
        buttonReg = findViewById(R.id.login_button)
        loginTextviewEmail = findViewById(R.id.login_textview_email)
        loginTextviewPassword = findViewById(R.id.login_textview_password)
    }
}