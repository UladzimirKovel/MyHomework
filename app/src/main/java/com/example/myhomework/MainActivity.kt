package com.example.myhomework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.new_fragment)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.newFragmentView, MainFragment(), "MainFragment")
                .commit()
        }
    }
}