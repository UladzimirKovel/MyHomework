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

class ThirdOnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third_onboarding)

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            goNext()
        }
    }

    private fun goNext() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@ThirdOnboardingActivity, MainActivity::class.java))
            finish()
        }, 0)
    }
}