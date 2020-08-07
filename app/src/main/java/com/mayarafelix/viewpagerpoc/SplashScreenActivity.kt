package com.mayarafelix.viewpagerpoc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimer: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screen)

        SharedPreferencesManager.init(applicationContext)

        Handler().postDelayed({
            checkSharedPreferences()
        }, splashTimer)
    }

    private fun launchMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchHomeActivity() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkSharedPreferences() {
        val isFistTimeUser = SharedPreferencesManager.getFirstTimeUserTag()

        if (isFistTimeUser) {
            SharedPreferencesManager.updateFirstTimeUserTag()
            launchMainActivity()
        } else {
            launchHomeActivity()
        }
    }
}