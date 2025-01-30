package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myhomework.R
import com.example.myhomework.data.repository.UserSharedPref
import com.example.myhomework.databinding.FragmentSignUpBinding
import com.example.myhomework.presentation.view_model.SignUpFragmentViewModel
import com.example.myhomework.presentation.view_model.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpModel: SignUpFragmentViewModel by inject()
    private val sharedPref: UserSharedPref by inject()
    private val userModel: UserViewModel by inject()

    //    private var sharedPref : UserSharedPref? = null
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
        
        /** Подписываемся на обновление и проверяем,сущ ли пользователь с таким email */
        userModel.registrUser.observe(viewLifecycleOwner, Observer { success ->
            success?.let {
                if (it) {
                    findNavController().navigate(R.id.loginFragment)
                    /** Дает возможность вернуться на предыдущий экран */
                    userModel.resetRegistrState()
                } else Toast.makeText(
                    requireContext(),
                    "User with this email exists",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        setupListener()
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
            loginSave.setOnClickListener {

                if (signUpModel.validateInput(
                        tvFirstname, tvLastname, tvEmail, tvPassword, requireContext()
                    )
                ) {
                    signUpModel.validateInput(
                        tvFirstname,
                        tvLastname,
                        tvEmail,
                        tvPassword,
                        requireContext()
                    )

                    sharedPref.saveUser(
                        tvFirstname.text.toString(),
                        tvLastname.text.toString(),
                        tvEmail.text.toString(),
                        tvPassword.text.toString(),
                    )

                    lifecycleScope.launch(Dispatchers.IO) {
                        userModel.registrationUser(
                            tvFirstname.text.toString(),
                            tvLastname.text.toString(),
                            tvEmail.text.toString(),
                            tvPassword.text.toString()
                        )
                    }
//                    findNavController().navigate(R.id.loginFragment)
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                    .commit()
//                goToNextFragment(SignUpFragment(), "SignUp")
                }
            }
        }

        binding.buttonBackMain.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, MainFragment(), "Main")
//                .commit()
//            goToNextFragment(MainFragment(), "Main")
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