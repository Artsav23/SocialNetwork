package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.socialnetwork.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInToAccount : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        binding.icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha))

        binding.registration.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }
        checkAuth()
        signIn()
    }


    private fun signIn() {
        binding.logIn.setOnClickListener {
            if (binding.email.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty()) {
                Toast.makeText(this, "Not all fields are filled in", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(binding.email.text.toString(), binding.password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    else{
                        Toast.makeText(this, "Incorrectly entered Email or Password",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkAuth() {
        if (auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
        }
    }

}