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
//        _binding = FragmentLoginBinding.bind(currentView)
//        return binding.root    Можно и так подключиться

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

            !isEmailValid(emailString) -> {
                Toast.makeText(context, "Incorrect Email validation", Toast.LENGTH_LONG).show()
                false
            }
            validationResPasswordSignup.isEmpty() -> {
                Toast.makeText(context, "Password string is empty", Toast.LENGTH_LONG).show()
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

        binding.loginButton.setOnClickListener {
            if (binding.loginTextviewEmail != null && binding.loginTextviewPassword != null) {
                validateInput(binding.loginTextviewEmail, binding.loginTextviewPassword)
            }
            goToNextFragment(LoginFragment(), "Login")
        }

        binding.buttonMain.setOnClickListener {
            goToNextFragment(MainFragment(), "Main")
        }

        binding.mainTextviewToLogin.setOnClickListener {
            goToNextFragment(SignUpFragment(), "SignUp")
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