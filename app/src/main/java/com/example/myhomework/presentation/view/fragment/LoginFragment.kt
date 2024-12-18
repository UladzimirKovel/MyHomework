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
import com.example.myhomework.data.repository.UserSharedPref
import com.example.myhomework.databinding.FragmentLoginBinding
import com.example.myhomework.domain.use_case.isEmailValid
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val sharedPref : UserSharedPref by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val currentView = inflater.inflate(R.layout.fragment_login, container, false)
//        return currentView

        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
    }

    private fun validateInput(
        email: EditText,
        password: EditText
    ): Boolean {
        val emailString = email.text.toString().trim()
        val validationResPasswordSignup = password.text.toString().trim()

        return when {
            emailString.isEmpty() -> {
                Toast.makeText(context, "Email string is empty", Toast.LENGTH_LONG).show()
                false
            }

            validationResPasswordSignup.isEmpty() -> {
                Toast.makeText(context, "Password string is empty", Toast.LENGTH_LONG).show()
                false
            }

            !isEmailValid(emailString) -> {
                Toast.makeText(context, "Incorrect Email validation", Toast.LENGTH_LONG).show()
                false
            }

            password.length() !in 8..30 -> {
                Toast.makeText(
                    context,
                    "Password length should be from 8 to 30 symbols",
                    Toast.LENGTH_LONG
                ).show()
                false
            }

            !isEmailExist(emailString, validationResPasswordSignup) -> {
                Toast.makeText(context, "Incorrect Email", Toast.LENGTH_LONG).show()
                false
            }
            else -> true
        }
    }

    private fun isEmailExist(email: String, password : String): Boolean {
        val userData = sharedPref.getUser()
        val savedEmail = userData["email"]
        val savedPassword = userData["password"]
        return if (savedEmail == email) {
            true
        } else if(savedPassword == password){
            true
        } else false
    }

    private fun setupListener(){
        val loginButtonMain: Button? = view?.findViewById(R.id.button_main)
        val accTextviewLogin: TextView? = view?.findViewById(R.id.main_textview_to_login)
        val buttonReg: Button? = view?.findViewById(R.id.login_button)
        val loginTextviewEmail: EditText? = view?.findViewById(R.id.login_textview_email)
        val loginTextviewPassword: EditText? = view?.findViewById(R.id.login_textview_password)

        buttonReg?.setOnClickListener {
            if (loginTextviewEmail != null && loginTextviewPassword != null) {
                    validateInput(loginTextviewEmail, loginTextviewPassword)
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, SignUpFragment(), "Login")
                .commit()
        }

        loginButtonMain?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, MainFragment(), "Main")
                .addToBackStack(null)
                .commit()
        }

        accTextviewLogin?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
                .addToBackStack(null)
                .commit()
        }
    }
}