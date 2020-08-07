package com.mayarafelix.viewpagerpoc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    lateinit var actionButton: Button
    private val firstTimeUserTag: String = "firstTimeUser"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        actionButton = findViewById(R.id.activity_home_button)
        actionButton.setOnClickListener {
            launchMainActivity()
        }
    }

    private fun launchMainActivity() {
        SharedPreferencesManager.removeFirstTimeUserTag()

        finish()
    }
}
