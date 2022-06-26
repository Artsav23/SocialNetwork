package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.example.socialnetwork.databinding.ActivityAddPudlicationBinding
import com.example.socialnetwork.databinding.ActivityMainBinding

class AddPudlication : AppCompatActivity() {
    lateinit var binding: ActivityAddPudlicationBinding
    var images=Images()
    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddPudlicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setImageResource(images.images[num])
        supportActionBar?.title="Add Publication"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.next.setOnClickListener {
            if (num==4){
                num=0
            }
            else{
            num++
            }
            binding.imageView.setImageResource(images.images[num])
        }
        binding.cancel.setOnClickListener {
            if (num==0){
                num=4
            }
            else{
            num--
            }
            binding.imageView.setImageResource(images.images[num])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_public, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add->{
                var data =Intent()
                data.putExtra("imageId", images.images[num].toString())
                data.putExtra("comment", "Comments: "+binding.comment.text.toString())
                    setResult(RESULT_OK, data)
                finish()
            }
        }
        return true
    }
}