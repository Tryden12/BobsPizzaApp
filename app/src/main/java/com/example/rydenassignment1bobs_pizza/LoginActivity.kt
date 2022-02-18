package com.example.rydenassignment1bobs_pizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.rydenassignment1bobs_pizza.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
        binding.forgotPassword.setOnClickListener{
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
        binding.guestContinue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun forgotPasswordTextView(view: android.view.View) {}
    fun guestContinueTextView(view: android.view.View) {}

}