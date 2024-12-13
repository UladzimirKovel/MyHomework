package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myhomework.R
import com.example.myhomework.databinding.FragmentMainBinding
import com.example.myhomework.presentation.action.MainFragmentActions
import com.example.myhomework.presentation.view_model.MyViewModel

private var _binding: FragmentMainBinding? = null
private val binding get() = _binding!!

private var viewModel: MyViewModel? = null

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
                    MyViewModel::class.java
                )

        _binding = FragmentMainBinding.inflate(this.layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()

        viewModel?.liveData?.observe(viewLifecycleOwner) {
            when {
                it.buttonAuto -> goToNextFragment(ListViewAutoFragment(), "ListAuto")
                it.buttonSignUp -> goToNextFragment(SignUpFragment(), "SignUp")
                it.buttonLogin -> goToNextFragment(LoginFragment(), "Login")
            }
        }
    }

    private fun setupListener() {

        binding.fragmentButtonAuto.setOnClickListener {
            viewModel?.handleAction(MainFragmentActions.GoToListAutoFragment)

//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, ListViewAutoFragment(), "AutoList")
//                .addToBackStack(null)
//                .commit()
        }
        binding.fragmentButtonSignUp.setOnClickListener {
            viewModel?.handleAction(MainFragmentActions.GoToSignUpFragment)

//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
//                .addToBackStack(null)
//                .commit()
        }

        binding.fragmentMainTextviewToLogin.setOnClickListener {
            viewModel?.handleAction(MainFragmentActions.GoToLoginFragment)

//            parentFragmentManager.beginTransaction()
//                .replace(R.id.newFragmentView, LoginFragment(), "Login")
//                .addToBackStack(null)
//                .commit()
        }
    }

    private fun goToNextFragment(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, fragment, tag)
                .addToBackStack(null)
                .commit()
    }
}