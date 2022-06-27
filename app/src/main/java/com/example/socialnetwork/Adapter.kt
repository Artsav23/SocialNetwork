package com.example.socialnetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.databinding.ViewBinding

class Adapter(private val listener: Listener): RecyclerView.Adapter<Adapter.ViewHolder>() {
    var publicationModel= mutableListOf<PublicationModel>()

    class ViewHolder(item:View):RecyclerView.ViewHolder(item) {
        var num = 1
        var binding=ViewBinding.bind(item)

        fun bind(publicationModel: PublicationModel, listener: Listener){
            binding.imageView3.setImageResource(publicationModel.imageId)
            binding.comments.text=publicationModel.comment
            itemView.setOnClickListener {
                listener.onClick(publicationModel)
            }

            binding.like.setOnClickListener {
                binding.textView2.text="Likes: "+ num.toString()
                num++
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(publicationModel[position], listener)
    }

    override fun getItemCount(): Int {
        return publicationModel.size
    }

    fun add (data: PublicationModel){
        publicationModel.add(data)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(data: PublicationModel)
    }
}