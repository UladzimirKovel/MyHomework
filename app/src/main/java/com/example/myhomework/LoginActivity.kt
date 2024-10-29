package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButtonMain: Button = findViewById(R.id.button_main)
        val accTextviewLogin: TextView = findViewById(R.id.main_textview_to_login)
        val loginTextviewEmail: EditText = findViewById(R.id.login_textview_email)
        val loginTextviewPassword: EditText = findViewById(R.id.login_textview_password)
        val buttonReg : Button = findViewById(R.id.login_button)

        buttonReg.setOnClickListener {
            val emailString = loginTextviewEmail.text.toString().trim()
            val validationResPasswordSignup = loginTextviewPassword.text.toString().trim()
            if (emailString == "" || validationResPasswordSignup == "") {
                Toast.makeText(this, "not all fields are filled in", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            } else if (!isEmailValid(emailString)) {
                Toast.makeText(this, "Error Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (loginTextviewPassword.length() <8 || loginTextviewPassword.length() > 255){
                Toast.makeText(this, "Length should be min 8", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }

        loginButtonMain.setOnClickListener {
            finish()
        }

        accTextviewLogin.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }


    }

}