package com.example.socialnetwork

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.databinding.ViewBinding

class ViewHolder(item: View): RecyclerView.ViewHolder(item) {

    private var num = 1
    private var binding = ViewBinding.bind(item)

    fun bind(publicationModel: PublicationModel, listener: Adapter.Listener) {
        binding.image.setImageResource(publicationModel.imageId)
        binding.commentsTextView.text = publicationModel.comment
        itemView.setOnClickListener {
            listener.onClick(publicationModel)
        }

        binding.like.setOnClickListener {
            binding.textView2.text = "Likes: $num"
            num++
        }
    }
}