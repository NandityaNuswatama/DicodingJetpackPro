package com.example.dicodingjetpackpro.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.data.local.entity.MyModelEntity
import com.example.dicodingjetpackpro.databinding.ItemReflectionBinding
import com.example.dicodingjetpackpro.utils.DiffUtilCallback
import com.example.dicodingjetpackpro.utils.imageLink

class FavAdapter: RecyclerView.Adapter<FavAdapter.FavViewHolder>() {
    private var list = ArrayList<MyModelEntity>()
    private var onItemClickCallback: OnItemClickCallback?= null

    interface OnItemClickCallback{
        fun onItemClicked(item: MyModelEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavViewHolder(private val binding: ItemReflectionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyModelEntity){
            with(binding){
                imgReflect.load(imageLink()+item.poster){
                    placeholder(R.drawable.icon_image)
                }
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = ItemReflectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataMovie(data: List<MyModelEntity>){
        val diffUtil = DiffUtilCallback(list, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = ArrayList(data)
        diffResult.dispatchUpdatesTo(this)
    }
    fun setDataShow(data: List<MyModelEntity>){
        val diffUtil = DiffUtilCallback(list, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = ArrayList(data)
        diffResult.dispatchUpdatesTo(this)
    }
}