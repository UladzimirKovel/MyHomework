package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myhomework.R
import com.example.myhomework.databinding.FragmentLoginBinding
import com.example.myhomework.presentation.view_model.LoginFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        setupListener()
    }

    private fun setupListener() {

//        val loginTextviewEmail: EditText? = view?.findViewById(R.id.login_textview_email)
//        val loginTextviewPassword: EditText? = view?.findViewById(R.id.login_textview_password)

        binding.loginButton.setOnClickListener {
            if (loginViewModel.validateInput(
                    binding.loginTextviewEmail,
                    binding.loginTextviewPassword,
                    requireContext()
                )
            )
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                .commit()
            findNavController().navigate(R.id.signUpFragment)
        }

        binding.buttonMain.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, MainFragment(), "Main")
//                .addToBackStack(null)
//                .commit()
        }

        binding.mainTextviewToLogin.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                .addToBackStack(null)
//                .commit()
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