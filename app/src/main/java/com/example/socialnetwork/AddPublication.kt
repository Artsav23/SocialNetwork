package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.socialnetwork.databinding.ActivityAddPudlicationBinding


class AddPublication : AppCompatActivity() {

    private lateinit var binding: ActivityAddPudlicationBinding
    private var images = Images()
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddPudlicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar()

        binding.next.setOnClickListener {
            next()
        }

        binding.cancel.setOnClickListener {
           cancel()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_public, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.add->{
                val data = Intent()
                data.putExtra("imageId", images.images[num].toString())
                data.putExtra("comment", "Comments: "+binding.comment.text.toString())
                    setResult(RESULT_OK, data)
                finish()
            }
        }
        return true
    }

    private fun setSupportActionBar() {
        supportActionBar?.title = "Add Publication"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun cancel() {
        if (num==0){
            num = 4
        }
        else{
            num--
        }

        binding.imageView.setImageResource(images.images[num])
    }

    fun next() {
        if (num==4){
            num = 0
        }
        else {
            num++
        }
        binding.imageView.setImageResource(images.images[num])
        }
}