package com.example.socialnetwork

import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.socialnetwork.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ViewModel (private var binding: ActivityMainBinding, private var supportActionBar: ActionBar?,
                private var supportFragmentManager: FragmentManager, private var adapter: Adapter) {

    val title = "Messenger"
    private var imageId = Uri.parse(R.drawable.ic_add.toString())
    private var comment = ""
    private var data = mutableListOf<PublicationModel>()
    private val auth = Firebase.auth

    fun onClickNavViewButtonMainActivity() {

        binding.bNavView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.add -> {
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, AddPublicationFragment(adapter)).commit()
                    supportActionBar?.title = "Add Publication"
                }

                R.id.main -> {
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, Publication(adapter)).commit()
                    supportActionBar?.title = "Social Network"
                }

                R.id.account -> {
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, AccountFragment(adapter)).commit()
                    supportActionBar?.title = auth.currentUser?.displayName
                }
            }
            true
        }
    }

    fun setSupport(viewModel: ViewModel){
        supportActionBar?.title = viewModel.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, Publication(adapter)).commit()
    }

    fun  activityForResult(it: ActivityResult){

        if (it.resultCode == AppCompatActivity.RESULT_OK) {

            if (!it.data?.getStringExtra("imageId").isNullOrEmpty()) {

                imageId = Uri.parse(it.data?.getStringExtra("imageId")!!.toString())
            }

            if (!it.data?.getStringExtra("comment").isNullOrEmpty()) {

                comment = it.data?.getStringExtra("comment")!!
            }

            data.add(PublicationModel(imageId, comment))
            adapter.add(data[data.size-1])
        }
    }
}