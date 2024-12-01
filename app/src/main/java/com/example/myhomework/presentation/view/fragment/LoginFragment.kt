package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myhomework.R
import com.example.myhomework.domain.use_case.isEmailValid

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_login, container, false)

        setupListener(currentView)

        return currentView
    }

    private fun validateInput(
        email: EditText,
        password: EditText
    ): Boolean {
        val emailString = email.text.toString().trim()
        val validationResPasswordSignup = password.text.toString().trim()

        return when {
            emailString.isEmpty() || validationResPasswordSignup.isEmpty() -> {
                Toast.makeText(context, "Password string is empty", Toast.LENGTH_LONG).show()
                false
            }

            !isEmailValid(emailString) -> {
                Toast.makeText(context, "Incorrect Email validation", Toast.LENGTH_LONG).show()
                false
            }

            password.length() < 8 || password.length() > 255 -> {
                Toast.makeText(
                    context,
                    "Password length should be from 8 to 255 symbols ",
                    Toast.LENGTH_LONG
                ).show()
                false
            }
            else -> true
        }
    }

    private fun setupListener(view: View){
        val loginButtonMain: Button = view.findViewById(R.id.button_main)
        val accTextviewLogin: TextView = view.findViewById(R.id.main_textview_to_login)
        val buttonReg: Button = view.findViewById(R.id.login_button)
        val loginTextviewEmail: EditText = view.findViewById(R.id.login_textview_email)
        val loginTextviewPassword: EditText = view.findViewById(R.id.login_textview_password)

        buttonReg.setOnClickListener {
            validateInput(loginTextviewEmail, loginTextviewPassword)
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, SignUpFragment(), "Login")
                .commit()
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
    }
}