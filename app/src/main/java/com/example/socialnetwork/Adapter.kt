package com.example.socialnetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.databinding.ViewBinding

class Adapter(private val listener: Listener): RecyclerView.Adapter<ViewHolder>() {

    private var publicationModel= mutableListOf<PublicationModel>()

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