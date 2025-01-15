package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myhomework.R
import com.example.myhomework.databinding.FragmentMainBinding
import com.example.myhomework.presentation.view_model.MainFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var _binding: FragmentMainBinding? = null
private val binding get() = _binding!!

private var viewModel: MainFragmentViewModel? = null

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val currentView = inflater.inflate(R.layout.fragment_main, container, false)
//        setupListener(currentView)
//        return currentView

        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(
                application = requireActivity()
                    .application
            )
                .create(
                    MainFragmentViewModel::class.java
                )

        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.network.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel?.apiResponce()
            }
        }

//        viewModel?.liveData?.observe(viewLifecycleOwner) {
//            when {
//                it.buttonAuto -> goToNextFragment(ListViewAutoFragment(), "ListAuto")
//                it.buttonSignUp -> goToNextFragment(SignUpFragment(), "SignUp")
//                it.buttonLogin -> goToNextFragment(LoginFragment(), "Login")
//            }
//        }

        setupListener()
    }

    private fun setupListener() {

        binding.fragmentButtonAuto.setOnClickListener {
//            viewModel?.handleAction(MainFragmentActions.GoToListAutoFragment)
            findNavController().navigate(R.id.listViewAutoFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, ListViewAutoFragment(), "AutoList")
//                .addToBackStack(null)
//                .commit()
        }
        binding.fragmentButtonSignUp.setOnClickListener {
//            viewModel?.handleAction(MainFragmentActions.GoToSignUpFragment)
            findNavController().navigate(R.id.signUpFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                .addToBackStack(null)
//                .commit()
        }

        binding.fragmentMainTextviewToLogin.setOnClickListener {
//            viewModel?.handleAction(MainFragmentActions.GoToLoginFragment)
            findNavController().navigate(R.id.loginFragment)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, LoginFragment(), "Login")
//                .addToBackStack(null)
//                .commit()
        }
    }

//    private fun goToNextFragment(fragment: Fragment, tag: String) {
//        parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, fragment, tag)
//                .addToBackStack(null)
//                .commit()
//    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}