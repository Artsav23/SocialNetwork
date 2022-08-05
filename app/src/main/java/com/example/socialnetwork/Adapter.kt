package com.example.socialnetwork

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val listener: Listener): RecyclerView.Adapter<ViewHolder>() {

    private var publicationModels = mutableListOf<PublicationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(publicationModels[position], listener)
    }

    override fun getItemCount(): Int {
        return publicationModels.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun add (data: PublicationModel) {
        publicationModels.add(data)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(data: PublicationModel)
    }
}