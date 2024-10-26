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

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButtonMain: Button = findViewById(R.id.button_main)
        val accTextviewLogin: TextView = findViewById(R.id.main_textview_to_login)
        val loginTextviewEmail: EditText = findViewById(R.id.login_textview_email)
        val loginTextviewPassword: EditText = findViewById(R.id.login_textview_password)
        val emailString = loginTextviewEmail.text.toString()

        loginTextviewEmail.doAfterTextChanged {
            if (isEmailValid(emailString)) {
                Toast.makeText(this, "Valid Email", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error Email", Toast.LENGTH_LONG).show()
            }
        }

        loginTextviewPassword.doAfterTextChanged { text ->
            when (val validationResPasswordSignup: ValidResult = isValidPassword(text.toString())) {
                is ValidResult.Valid -> {
                    loginTextviewPassword.error = null
                }

                is ValidResult.Invalid -> {
                    loginTextviewPassword.error = getString(validationResPasswordSignup.errorRes)
                }
            }

        }

        loginButtonMain.setOnClickListener {
            onBackPressed()
        }

        accTextviewLogin.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }


    }

}