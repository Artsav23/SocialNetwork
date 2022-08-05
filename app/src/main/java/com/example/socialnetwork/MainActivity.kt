package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.socialnetwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Adapter.Listener {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityMainBinding
    private var adapter = Adapter(this)
    private lateinit var viewModel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAllFun()
    }

    override fun onClick(data: PublicationModel) {
    startActivity(Intent(this, PublicationRecycleView::class.java).apply {
        putExtra("item", data)
        })
    }

    private fun startActivityForResult() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.activityForResult(it)
        }
    }

    private fun initAllFun() {
        viewModel = ViewModel(binding, supportActionBar, supportFragmentManager, adapter)
        viewModel.onClickNavViewButtonMainActivity()
        startActivityForResult()
        viewModel.setSupport(viewModel)
    }



}