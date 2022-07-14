package com.example.socialnetwork

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity

class ViewModel {

    val title = "Message"
    private var imageId = R.drawable.ic_add
    private var comment = ""
    private var data = mutableListOf<PublicationModel>()

    fun  onClickNavViewButton(itemId: Int, packageContext: Context, launcher: ActivityResultLauncher<Intent>) {
        when(itemId) {
            R.id.add-> {
                val intent = Intent(packageContext, AddPublication::class.java)
                launcher.launch(intent)
            }
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