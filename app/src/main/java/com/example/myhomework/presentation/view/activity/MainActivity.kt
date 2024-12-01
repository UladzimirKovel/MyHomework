package com.example.myhomework.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myhomework.R
import com.example.myhomework.presentation.view.fragment.MainFragment

class MainActivity : AppCompatActivity() {

//    private var _binding: ActivityMainBinding? = null
//    private val binding get() = _binding!!
//
//    private var viewModel: MyViewModel? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//            .create(MyViewModel::class.java)
//        _binding = ActivityMainBinding.inflate(this.layoutInflater)
//        setContentView(binding.root)

        setContentView(R.layout.activity_main)

//        observeLiveData()
//        initClick()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.newFragmentView, MainFragment(), "MainFragment")
                .commit()
        }
    }

//    private fun initClick() {
//        _binding?.buttonExample?.setOnClickListener {
//            viewModel?.updateTextField()
//        }
//    }
//
//    private fun observeLiveData() {
//        viewModel?.liveData?.observe(this) { newData ->
//            _binding?.tvExample?.text = newData
//        }
//    }
//
//    override fun onDestroy() {
//        _binding = null
//        super.onDestroy()
//    }
}