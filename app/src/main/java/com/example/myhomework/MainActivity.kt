package com.example.myhomework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var autoButton: TextView? = null
    private var buttonMain: Button? = null
    private var mainTextViewToLogin: TextView? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        initClick()
    }

    private fun initClick() {

        val autoButton: TextView = findViewById(R.id.button_auto)
        val buttonMain: Button = findViewById(R.id.button_main)
        val mainTextViewToLogin: TextView = findViewById(R.id.main_textview_to_login)

        autoButton.setOnClickListener {
            startActivity(Intent(this, ListViewAuto::class.java))
        }

        buttonMain.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        mainTextViewToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun initialize() {
        autoButton = findViewById(R.id.button_auto)
        buttonMain = findViewById(R.id.button_main)
        mainTextViewToLogin = findViewById(R.id.main_textview_to_login)
    }
}