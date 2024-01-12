package com.example.advnative_waroengujang.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.database.LocalPreference
import com.example.advnative_waroengujang.model.User
import com.example.advnative_waroengujang.viewmodel.CartViewModel
import com.example.advnative_waroengujang.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var pref: LocalPreference
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = LocalPreference(this)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        checkLoginSession(this)

        val txtUsername = findViewById<EditText>(R.id.txtUsername)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        val username = txtUsername.text.toString().trim()
        val password = txtPassword.text.toString().trim()

        btnSignIn.setOnClickListener {
            loginViewModel.login(username, password).observe(this) { isValid ->
                if (isValid) {
                    pref.startSession(1)
                    Log.e("LoginActivity", "IsValid")
                    goToMainActivity(this)
                } else {
                    Log.e("LoginActivity", "Not Valid")
                    Toast.makeText(this, "Invalid Username/Password!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkLoginSession(context: Context) {
        val isLoggedIn = pref.isLoggedIn() ?: false

        if (isLoggedIn) {
            goToMainActivity(context)
        }
    }

    private fun goToMainActivity(context: Context) {
        startActivity(Intent(context, MainActivity::class.java))
    }
}