package com.example.myhomework

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondOnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_onboarding)

        CoroutineScope(Dispatchers.Main).launch {
            delay(5001)
            startActivity(
                Intent(
                    this@SecondOnboardingActivity,
                    ThirdOnboardingActivity::class.java
                )
            )
        }
    }
}