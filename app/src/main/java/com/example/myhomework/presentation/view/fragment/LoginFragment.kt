package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myhomework.R
//import com.example.myhomework.data.repository.UserSharedPref
import com.example.myhomework.databinding.FragmentLoginBinding
import com.example.myhomework.presentation.view_model.LoginFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
//import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    //    private val sharedPref: UserSharedPref by inject()
    private val loginViewModel: LoginFragmentViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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

        setupListener(binding.root)
    }

    private fun setupListener(view: View) {

        val loginButtonMain: Button = view.findViewById(R.id.button_main)
        val accTextviewLogin: TextView = view.findViewById(R.id.main_textview_to_login)
        val buttonReg: Button = view.findViewById(R.id.login_button)
        val loginTextviewEmail: EditText = view.findViewById(R.id.login_textview_email)
        val loginTextviewPassword: EditText = view.findViewById(R.id.login_textview_password)

        buttonReg.setOnClickListener {
            if(loginViewModel.validateInput(loginTextviewEmail, loginTextviewPassword, requireContext()))

            goToNextFragment(SignUpFragment(), "Login")
        }

        loginButtonMain.setOnClickListener {
            goToNextFragment(MainFragment(), "Main")
        }

        accTextviewLogin.setOnClickListener {
            goToNextFragment(SignUpFragment(), "SignUp")
        }
    }

    private fun goToNextFragment(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.newFragmentView, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}