package com.example.socialnetwork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val listener: Listener): RecyclerView.Adapter<ViewHolder>() {

    private var publicationModels = mutableListOf(
        PublicationModel(R.drawable.ic_add, "1"),
        PublicationModel( R.drawable.ic_message, "2"),
        PublicationModel(com.google.android.material.R.drawable.abc_btn_check_material, "3"),
        PublicationModel(R.drawable.ic_launcher_foreground, "4"),
        PublicationModel(R.drawable.ic_baseline_home_24, "5"))

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

    fun add (data: PublicationModel) {
        publicationModels.add(data)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(data: PublicationModel)
    }
}