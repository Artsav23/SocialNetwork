package com.example.socialnetwork

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.socialnetwork.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ViewModel {

    val title = "Messenger"
    private var imageId = R.drawable.ic_add
    private var comment = ""
    private var data = mutableListOf<PublicationModel>()
    private val auth = Firebase.auth

    fun onClickNavViewButton(binding: ActivityMainBinding, supportActionBar: ActionBar?,
                             supportFragmentManager: FragmentManager, adapter: Adapter) {

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
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, AccountFragment.newInstance()).commit()
                    supportActionBar?.title = auth.currentUser?.displayName
                }
            }
            true
        }
    }

    fun  activityForResult(it: ActivityResult, adapter: Adapter){
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            if (!it.data?.getStringExtra("imageId").isNullOrEmpty()) {
                imageId = it.data?.getStringExtra("imageId")!!.toInt()
            }

            if (!it.data?.getStringExtra("comment").isNullOrEmpty()) {
                comment = it.data?.getStringExtra("comment")!!
            }

            data.add(PublicationModel(imageId, comment))
            adapter.add(data[data.size-1])
        }
    }
}