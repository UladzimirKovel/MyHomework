package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myhomework.R
import com.example.myhomework.data.repository.UserSharedPref
import com.example.myhomework.databinding.FragmentSignUpBinding
import com.example.myhomework.presentation.view_model.SignUpFragmentViewModel
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment() {
    //    private var sharedPref : UserSharedPref? = null
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val signUpModel : SignUpFragmentViewModel by inject()
    private val sharedPref : UserSharedPref by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val currentView = inflater.inflate(R.layout.fragment_sign_up, container, false)
//        setupListener(currentView)
//        return currentView

        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener(requireView())
    }

    private fun setupListener(view: View) {

//        val signupButtonMain: Button = view.findViewById(R.id.button_signup)
//        val accTextViewSignup: TextView = view.findViewById(R.id.main_textview_to_login)
//        val buttonReg: Button = view.findViewById(R.id.login_button)
        val signupTextViewFirstname: EditText = view.findViewById(R.id.signup_textview_firstname)
        val signupTextViewLastname: EditText = view.findViewById(R.id.signup_textview_lastname)
        val signupTextViewEmail: EditText = view.findViewById(R.id.email)
        val signupTextViewPassword: EditText = view.findViewById(R.id.password)

        binding.loginButton.setOnClickListener {
            if (signUpModel.validateInput(
                    signupTextViewFirstname,
                    signupTextViewLastname,
                    signupTextViewEmail,
                    signupTextViewPassword,
                    requireContext()
                )
            ) {
                sharedPref.saveUser(
                    signupTextViewFirstname.toString(),
                    signupTextViewLastname.toString(),
                    signupTextViewEmail.toString(),
                    signupTextViewPassword.toString()
                )
                findNavController().navigate(R.id.signUpFragment)
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                    .commit()
//                goToNextFragment(SignUpFragment(), "SignUp")
            }
        }

        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, MainFragment(), "Main")
//                .commit()
//            goToNextFragment(MainFragment(), "Main")
        }

        binding.mainTextviewToLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, LoginFragment(), "Login")
//                .commit()
//            goToNextFragment(LoginFragment(), "Login")
        }

    }

//    private fun goToNextFragment(fragment: Fragment, tag: String) {
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.newFragmentView, fragment, tag)
//            .addToBackStack(null)
//            .commit()
//    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}