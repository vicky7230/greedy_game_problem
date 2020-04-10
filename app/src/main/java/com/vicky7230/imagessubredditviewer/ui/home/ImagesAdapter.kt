package com.vicky7230.imagessubredditviewer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vicky7230.imageloader.ImageLoader
import com.vicky7230.imagessubredditviewer.R
import com.vicky7230.imagessubredditviewer.data.network.ImageUrl
import com.vicky7230.imagessubredditviewer.utils.CommonUtils.isValidUrl
import kotlinx.android.synthetic.main.image_list_item.view.*
import java.util.*

class ImagesAdapter(private var images: ArrayList<ImageUrl>) :
    RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {
    interface Callback {
        fun onItemClick(imageUrl: ImageUrl)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback?) {
        this.callback = callback
    }

    fun updateItems(images: ArrayList<ImageUrl>) {
        this.images.clear()
        this.images = images
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<ImageUrl> {
        return images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val newsViewHolder = ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        )
        newsViewHolder.itemView.setOnClickListener { v: View? ->
            val position = newsViewHolder.adapterPosition
            if (callback != null) {
                callback!!.onItemClick(images[position])
            }
        }
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(imageUrl: ImageUrl) {
            if (isValidUrl(imageUrl.thumbnailUrl)) {
                itemView.image.setImageDrawable(null)
                ImageLoader.displayImage(imageUrl.thumbnailUrl, itemView.image)
            } else {
                itemView.image.setImageResource(R.drawable.ic_warning)
            }
        }
    }

}