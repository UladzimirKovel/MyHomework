package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstOnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_onbording)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            goNext()
        }
    }

    private fun goNext() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this@FirstOnboardingActivity,
                    SecondOnboardingActivity::class.java
                )
            )
            finish()
        }, 0)
    }
}