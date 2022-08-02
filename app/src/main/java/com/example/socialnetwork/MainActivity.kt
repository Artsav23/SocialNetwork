package com.example.socialnetwork

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socialnetwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Adapter.Listener {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding
    private var adapter = Adapter(this)
    private val viewModel = ViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.onClickNavViewButton(binding,supportActionBar, supportFragmentManager, adapter)
        startActivityForResult()
        setSupport()
    }

    override fun onClick(data: PublicationModel) {
    startActivity(Intent(this, PublicationRecycleView::class.java).apply {
        putExtra("item", data)
        })
    }

    private fun setSupport() {
        supportActionBar?.title = viewModel.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, Publication(adapter)).commit()
    }

    private fun startActivityForResult() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.activityForResult(it, adapter)
        }
    }


}