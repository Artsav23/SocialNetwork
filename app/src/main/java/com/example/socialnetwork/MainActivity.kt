package com.example.socialnetwork

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

        binding.bNavView.setOnItemSelectedListener {
            viewModel.onClickNavViewButton(it.itemId, this, launcher)
            true
        }

        startActivityForResult()
        setSupportTitle()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(data: PublicationModel) {
    startActivity(Intent(this, PublicationRecycleView::class.java).apply {
        putExtra("item", data)
        })
    }

    private fun setSupportTitle() {
        supportActionBar?.title = viewModel.title
    }

    private fun startActivityForResult() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.activityForResult(it, adapter)
        }
    }
}