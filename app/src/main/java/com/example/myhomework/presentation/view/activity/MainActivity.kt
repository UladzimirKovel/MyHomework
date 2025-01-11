package com.example.myhomework.presentation.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myhomework.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar:Toolbar
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    private lateinit var appBarConfiguration:AppBarConfiguration

//    private var toolbar:Toolbar? = null
//    private var drawerLayout:DrawerLayout? = null
//    private var navController: NavController? = null
//    private var navigationView: NavigationView? = null
//    private var appBarConfiguration:AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigation_view)

//        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)
//        drawerLayout.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.listViewAutoFragment, R.id.loginFragment, R.id.signUpFragment))
        setupActionBarWithNavController(navController,drawerLayout)
        navigationView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
//    private var _binding: ActivityMainBinding? = null
//    private val binding get() = _binding!!
//
//    private var viewModel: MyViewModel? = null

//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//            .create(MyViewModel::class.java)
//        _binding = ActivityMainBinding.inflate(this.layoutInflater)
//        setContentView(binding.root)

//        setContentView(R.layout.activity_main)

//        observeLiveData()
//        initClick()
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.newFragmentView, NavHostFragment(), "MainFragment")
//                .addToBackStack(null)
//                .commit()
//        }
//    }

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
