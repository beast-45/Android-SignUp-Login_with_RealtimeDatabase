package com.example.accessdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.accessdatabase.databinding.ActivityWelcomeScreen2Binding
import com.example.accessdatabase.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {
    lateinit var binding: ActivityWelcomeScreen2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityWelcomeScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(LoginActivity.key2).toString()
        val mail = intent.getStringExtra(LoginActivity.key1).toString()
        val password = intent.getStringExtra(LoginActivity.key3).toString()

        binding.Welcomename.setText("Welcome $name")
        binding.EmailText.setText("Your Email is $mail")
        binding.passtext.setText("Your Password is $password")
    }
}