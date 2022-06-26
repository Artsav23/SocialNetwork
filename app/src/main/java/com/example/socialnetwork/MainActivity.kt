package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialnetwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Adapter.Listener {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var launcherView: ActivityResultLauncher<Intent>
    lateinit var binding: ActivityMainBinding
    var adapter=Adapter(this)
    var data= mutableListOf<DataView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title="Messeger"

        launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if (it.resultCode== RESULT_OK){
                    data.add(DataView(it.data?.getStringExtra("imageId")?.toInt()!!,
                        it.data?.getStringExtra("comment")!!))
                    adapter.add(data[data.size-1])
                }
        }

        binding.bNavView.setOnItemSelectedListener {
            when(it.itemId){
               R.id.add->{
                   var intent= Intent(this, AddPudlication::class.java)
                    launcher.launch(intent)
               }
            }
            true
        }
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    fun init(){
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
    }

    override fun onClick(data: DataView) {
    startActivity(Intent(this, OneView::class.java).apply {
        putExtra("item", data)
    })

//            putExtra("imageId", data.imageId.toString())
//            putExtra("comment", data.comment)

    }

}