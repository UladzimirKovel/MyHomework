package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myhomework.R
import com.example.myhomework.data.repository.UserSharedPref
import com.example.myhomework.databinding.FragmentSignUpBinding
import com.example.myhomework.domain.use_case.isEmailValid
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment() {
    //    private var sharedPref : UserSharedPref? = null
    private val sharedPref: UserSharedPref by inject()
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val currentView = inflater.inflate(R.layout.fragment_sign_up, container, false)
//        setupListener(currentView)
//        return currentView

        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        setupListener()

        return binding.root
    }

    private fun setupListener() {

//        val signupButtonMain: Button = view.findViewById(R.id.button_signup)
//        val accTextViewSignup: TextView = view.findViewById(R.id.main_textview_to_login)
//        val buttonReg: Button = view.findViewById(R.id.login_button)
//        val signupTextViewFirstname: EditText = view.findViewById(R.id.signup_textview_firstname)
//        val signupTextViewLastname: EditText = view.findViewById(R.id.signup_textview_lastname)
//        val signupTextViewEmail: EditText = view.findViewById(R.id.email)
//        val signupTextViewPassword: EditText = view.findViewById(R.id.password)

        binding.apply {
            loginButton.setOnClickListener {
                if (validateInput(
                        signupTextviewFirstname,
                        signupTextviewLastname,
                        email,
                        password
                    )
                ) {
                    sharedPref.saveUser(
                        signupTextviewFirstname.toString(),
                        signupTextviewLastname.toString(),
                        email.toString(),
                        password.toString()
                    )
                    goToNextFragment(LoginFragment(), "Login")
                }
            }

            buttonSignup.setOnClickListener {
                goToNextFragment(MainFragment(), "Main")
            }
            mainTextviewToLogin.setOnClickListener {
                goToNextFragment(LoginFragment(), "Login")
            }
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

            isNameValid.isEmpty() -> {
                Toast.makeText(context, "First Name string is empty", Toast.LENGTH_LONG).show()
                false
            }

            firstName.length() !in 3..25 -> {
                Toast.makeText(
                    context,
                    "First Name length should be from 3 to 25 symbols ",
                    Toast.LENGTH_LONG
                )
                    .show()
                false
            }

            isLastNameValid.isEmpty() -> {
                Toast.makeText(context, "Last Nave string is empty", Toast.LENGTH_LONG).show()
                false
            }

            lastName.length() !in 3..25 -> {
                Toast.makeText(
                    context,
                    "Last Name length should be from 3 to 25 symbols",
                    Toast.LENGTH_LONG
                )
                    .show()
                false
            }

            emailString.isEmpty() -> {
                Toast.makeText(context, "Email string is empty", Toast.LENGTH_LONG).show()
                false
            }

            !isEmailValid(emailString) -> {
                Toast.makeText(context, "Incorrect Email validation", Toast.LENGTH_LONG).show()
                false
            }

            isValidPassword.isEmpty() -> {
                Toast.makeText(context, "Password string is empty", Toast.LENGTH_LONG).show()
                false
            }

            password.length() !in 8..30 -> {
                Toast.makeText(
                    context,
                    "Password length should be from 8 to 30 symbols",
                    Toast.LENGTH_LONG
                )
                    .show()
                false
            }

            else -> true
        }
    }

    private fun goToNextFragment(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.newFragmentView, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}