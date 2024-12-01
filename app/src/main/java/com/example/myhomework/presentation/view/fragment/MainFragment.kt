package com.example.myhomework.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myhomework.R


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_main, container, false)

        setupListener(currentView)

        return currentView
    }
    private fun setupListener(view: View) {

        val autoButton: TextView = view.findViewById(R.id.fragment_button_auto)
        val buttonMain: Button = view.findViewById(R.id.fragment_button_main)
        val mainTextViewToLogin: TextView =view.findViewById(R.id.fragment_main_textview_to_login)

        autoButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, ListViewAutoFragment(), "AutoList")
                .addToBackStack(null)
                .commit()
        }
        buttonMain.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, SignUpFragment(), "SignUp")
                .addToBackStack(null)
                .commit()

        }

        mainTextViewToLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.newFragmentView, LoginFragment(), "Login")
                .addToBackStack(null)
                .commit()
        }
    }
}