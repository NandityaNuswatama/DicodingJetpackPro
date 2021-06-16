package com.example.dicodingjetpackpro.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.databinding.ItemCastBinding
import com.example.dicodingjetpackpro.model.MyCast
import com.example.dicodingjetpackpro.utils.DiffUtilCallback
import com.example.dicodingjetpackpro.utils.imageLink

class ActorsAdapter: RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {
    private var listItem = ArrayList<MyCast>()
    private var onItemClickCallback: OnItemClickCallback?= null
    
    interface OnItemClickCallback{
        fun onItemClicked(myCast: MyCast)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    
    inner class ActorsViewHolder(private val binding: ItemCastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: MyCast){
            binding.apply {
                name.text = cast.name
                avatar.load(imageLink()+cast.photo) {
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.icon_image)
                }
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(cast)
                }
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorsViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        return holder.bind(listItem[position])
    }
    
    override fun getItemCount(): Int {
        return listItem.size
    }
    
    fun setData(newList: ArrayList<MyCast>){
        val diffUtil = DiffUtilCallback(listItem, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listItem = newList
        diffResult.dispatchUpdatesTo(this)
    }
}