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

class SignUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_sign_up, container, false)

        setupListener(currentView)

        return currentView
    }

    private fun setupListener(view: View) {

        val signupButtonMain: Button = view.findViewById(R.id.button_signup)
        val accTextViewSignup: TextView = view.findViewById(R.id.main_textview_to_login)
        val signupTextViewFirstname: EditText = view.findViewById(R.id.signup_textview_firstname)
        val signupTextViewLastname: EditText = view.findViewById(R.id.signup_textview_lastname)
        val signupTextViewEmail: EditText = view.findViewById(R.id.email)
        val signupTextViewPassword: EditText = view.findViewById(R.id.password)
        val buttonReg: Button = view.findViewById(R.id.login_button)

        buttonReg.setOnClickListener {
            if (validateInput(
                    signupTextViewFirstname,
                    signupTextViewLastname,
                    signupTextViewEmail,
                    signupTextViewPassword
                )
            ) {
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

    }

    private fun validateInput(
        firstName: EditText,
        lastName: EditText,
        email: EditText,
        password: EditText
    ): Boolean {

        val isNameValid = firstName.text.toString().trim()
        val isLastNameValid = lastName.text.toString().trim()
        val emailString = email.text.toString().trim()
        val isValidPassword = password.text.toString().trim()

        return when {
            emailString.isEmpty() ||
                    isValidPassword.isEmpty() ||
                    isNameValid.isEmpty() ||
                    isLastNameValid.isEmpty() -> {
                Toast.makeText(context, "Login string is empty", Toast.LENGTH_LONG).show()
                false
            }

            !isEmailValid(emailString) -> {
                Toast.makeText(context, "Incorrect Email validation", Toast.LENGTH_LONG).show()
                false
            }

            password.length() < 8 ||
                    firstName.length() < 8 ||
                    lastName.length() < 8 -> {
                Toast.makeText(context, "Length should be more then 8 symbols ", Toast.LENGTH_LONG)
                    .show()
                false
            }

            else -> true
        }

    }

}