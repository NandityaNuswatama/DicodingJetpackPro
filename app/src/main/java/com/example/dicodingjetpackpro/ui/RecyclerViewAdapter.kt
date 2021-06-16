package com.example.dicodingjetpackpro.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.databinding.ItemGeneralBinding
import com.example.dicodingjetpackpro.model.MyModel
import com.example.dicodingjetpackpro.utils.imageLink

class RecyclerViewAdapter: PagingDataAdapter<MyModel, RecyclerViewAdapter.HomeViewHolder>(RVDiffUtilCallBack()) {
    private var onItemClickCallback: OnItemClickCallback?= null

    interface OnItemClickCallback{
        fun onItemClicked(myModel: MyModel)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class HomeViewHolder(private val binding: ItemGeneralBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(myModel: MyModel) {
            with(binding) {
                itemTitle.text = myModel.title
                itemDate.text = myModel.date
                poster.load(imageLink() + myModel.poster) {
                    placeholder(R.drawable.icon_image)
                }
                rating.text = myModel.rating.toString()
                ratingBar.rating = (myModel.rating!!.div(2)).toFloat()
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(myModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = ItemGeneralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        setFadeAnimation(holder.itemView)
        return holder.bind(getItem(position)!!)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 400
        view.startAnimation(anim)
    }

    class RVDiffUtilCallBack: DiffUtil.ItemCallback<MyModel>() {
        override fun areItemsTheSame(oldItem: MyModel, newItem: MyModel): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: MyModel, newItem: MyModel): Boolean {
            return oldItem == newItem
        }
    }
}