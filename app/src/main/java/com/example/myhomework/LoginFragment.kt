package com.example.myhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_login, container, false)

        val loginButtonMain: Button = currentView.findViewById(R.id.button_main)
        val accTextviewLogin: TextView = currentView.findViewById(R.id.main_textview_to_login)
        val loginTextviewEmail: EditText = currentView.findViewById(R.id.login_textview_email)
        val loginTextviewPassword: EditText = currentView.findViewById(R.id.login_textview_password)
        val buttonReg: Button = currentView.findViewById(R.id.login_button)

        buttonReg.setOnClickListener {
            val emailString = loginTextviewEmail.text.toString().trim()
            val validationResPasswordSignup = loginTextviewPassword.text.toString().trim()
            if (emailString == "" || validationResPasswordSignup == "") {
                return@setOnClickListener
            } else if (!isEmailValid(emailString)) {
                return@setOnClickListener
            } else if (loginTextviewPassword.length() < 8 || loginTextviewPassword.length() > 255) {
                return@setOnClickListener
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.newFragmentView, SignUpFragment(), "Login")
                    .commit()
            }
        }

        loginButtonMain.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, MainFragment(), "Login")
                .addToBackStack(null)
                .commit()
        }

        accTextviewLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, SignUpFragment(), "Login")
                .addToBackStack(null)
                .commit()
        }
        return currentView
    }

}