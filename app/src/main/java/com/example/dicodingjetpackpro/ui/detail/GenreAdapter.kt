package com.example.dicodingjetpackpro.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingjetpackpro.databinding.ItemGenreBinding

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private var listItem = ArrayList<String>()
    inner class GenreViewHolder(private val binding: ItemGenreBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String){
            binding.genre.text = genre
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.GenreViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: GenreAdapter.GenreViewHolder, position: Int) {
        return holder.bind(listItem[position])
    }
    
    override fun getItemCount(): Int {
        return listItem.size
    }
    
    fun setData(data: List<String>){
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }
}