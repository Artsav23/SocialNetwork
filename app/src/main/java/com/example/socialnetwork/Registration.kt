package com.example.socialnetwork

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.socialnetwork.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class Registration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private var avatar:  Uri? = null
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        auth = Firebase.auth
        storage = Firebase.storage
        setContentView(binding.root)
        avatar = Uri.parse( binding.avatar.toString())

        endRegistration()
        chooseAvatar()
        openGallery()

    }

    private fun endRegistration() {
        binding.endReg.setOnClickListener {
            if (binding.email.text.isNullOrEmpty() || binding.name.text.isNullOrEmpty()
                || binding.password.text.isNullOrEmpty()) {
                Toast.makeText(this, "Not all fields are filled in", Toast.LENGTH_SHORT).show()
            }
            else {
                if (binding.password.text.count() < 8){
                    Toast.makeText(this, "Password should be a minimum 8 symbols", Toast.LENGTH_SHORT).show()
                }
                else {
                    auth.createUserWithEmailAndPassword(binding.email.text.toString(), binding.password.text.toString()).addOnCompleteListener {
                        if (!it.isSuccessful) {
                        Toast.makeText(this, "Check Email", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            storage.reference.child("avatars/"+auth.currentUser?.uid).putFile(avatar!!)
                            auth.currentUser?.updateProfile(userProfileChangeRequest {
                                displayName = binding.name.text.toString()
                                photoUri = Uri.parse(binding.avatar.toString())
                                })
                            Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,  MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun openGallery() {
        binding.openGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            launcher.launch(intent)
        }
    }

    private fun chooseAvatar(){
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                binding.avatar.setImageURI(it.data?.data)
                avatar = it.data?.data
            }
        }
    }
}