package com.example.myhomework.presentation.view.fragment

//import com.example.myhomework.data.repository.UserSharedPref
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
import com.example.myhomework.databinding.FragmentLoginBinding
import com.example.myhomework.presentation.view_model.LoginFragmentViewModel
import com.example.myhomework.presentation.view_model.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

//    private val sharedPref: UserSharedPref by inject()
    private val loginViewModel: LoginFragmentViewModel by viewModel()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userModel: UserViewModel by inject()

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

        userModel.registrUser.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                if (user) {
                    findNavController().navigate(R.id.listViewAutoFragment)
                    userModel.resetRegistrState()
                } else Toast.makeText(
                    requireContext(),
                    "Incorrect email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        setupListener()
    }

    private fun setupListener() {

//        buttonReg?.setOnClickListener {
//            if (loginTextviewEmail != null && loginTextviewPassword != null) {
//                validateInput(loginTextviewEmail, loginTextviewPassword)
//            }
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                .addToBackStack("null")
//                .commit()

//        val loginTextviewEmail: EditText? = view?.findViewById(R.id.login_textview_email)
//        val loginTextviewPassword: EditText? = view?.findViewById(R.id.login_textview_password)
        _binding?.apply {
            loginCheck.setOnClickListener {

                if (loginViewModel.validateInput(
                        loginTvEmail,
                        loginTvPassword,
                        requireContext()
                    )
                ) {

                    lifecycleScope.launch(Dispatchers.IO) {
                        userModel.loginUser(
                            loginTvEmail.text.toString(),
                            loginTvPassword.text.toString()
                        )
                    }

                }

//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                    .commit()
            }
        }

        binding.mainMenuTextview.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, MainFragment(), "Main")
//                .addToBackStack(null)
//                .commit()
        }

        binding.signUpTextview.setOnClickListener {
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