package com.example.myhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val signupButtonMain: Button = currentView.findViewById(R.id.button_signup)
        val accTextViewSignup: TextView = currentView.findViewById(R.id.main_textview_to_login)

        val signupTextViewFirstname: EditText = currentView.findViewById(R.id.signup_textview_firstname)
        val signupTextViewLastname: EditText = currentView.findViewById(R.id.signup_textview_lastname)
        val signupTextViewEmail: EditText = currentView.findViewById(R.id.email)
        val signupTextViewPassword: EditText = currentView.findViewById(R.id.password)
        val buttonReg: Button = currentView.findViewById(R.id.login_button)

        buttonReg.setOnClickListener {
            val isNameValid = signupTextViewFirstname.text.toString().trim()
            val isLastNameValid = signupTextViewLastname.text.toString().trim()
            val emailString = signupTextViewEmail.text.toString().trim()
            val isValidPassword = signupTextViewPassword.text.toString().trim()

            if (emailString == "" ||
                isLastNameValid == "" ||
                isNameValid == "" ||
                isValidPassword == ""
            ) {
                return@setOnClickListener
            } else if (!isEmailValid(emailString)) {
                return@setOnClickListener
            } else if ((signupTextViewPassword.length() < 8) ||
                (signupTextViewFirstname.length() < 8) ||
                (signupTextViewLastname.length() < 8)
            ) {
                return@setOnClickListener
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.newFragmentView, LoginFragment(), "Login")
                    .commit()
            }
        }

        signupButtonMain.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, MainFragment(), "Login")
                .commit()
        }

        accTextViewSignup.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, LoginFragment(), "Login")
                .commit()
        }
        return currentView
    }
}