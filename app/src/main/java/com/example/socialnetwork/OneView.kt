package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.socialnetwork.databinding.ActivityOneViewBinding

class OneView : AppCompatActivity() {
    lateinit var binding: ActivityOneViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOneViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item=intent.getSerializableExtra("item") as PublicationModel
        binding.imageView3.setImageResource(item.imageId)
        binding.comments.text=item.comment
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var data = Intent()
        setResult(RESULT_CANCELED,data)
        finish()
        return true
    }
}