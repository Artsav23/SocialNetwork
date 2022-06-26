package com.example.socialnetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.databinding.ViewBinding

class Adapter(val listener: Listener): RecyclerView.Adapter<Adapter.ViewHolder>() {
    var dataView= mutableListOf<DataView>()

    class ViewHolder(item:View):RecyclerView.ViewHolder(item) {
        var num = 1
        var binding=ViewBinding.bind(item)
        fun bind(dataView: DataView, listener: Listener){
            binding.imageView3.setImageResource(dataView.imageId)
            binding.comments.text=dataView.comment
            itemView.setOnClickListener {
                listener.onClick(dataView)
            }
            binding.like.setOnClickListener {
                binding.textView2.text="Likes: "+ num.toString()
                num++
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataView[position], listener)
    }

    override fun getItemCount(): Int {
        return dataView.size
    }
    fun add (data: DataView){
        dataView.add(data)
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(data: DataView)
    }
}