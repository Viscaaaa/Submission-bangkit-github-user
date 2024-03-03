package com.visca.subgithub

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.visca.subgithub.data.ItemsItem
import com.visca.subgithub.databinding.DataUserBinding

class FavoriteUserAdapter(private val  listUser: ArrayList<ItemsItem>) : RecyclerView.Adapter<FavoriteUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.data_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Glide.with(holder.imgPhoto)
            .load(listUser[position].avatarUrl)
            .into(holder.imgPhoto)
        holder.tvUsername.text = listUser[position].login
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[position]) }
    }

    override fun getItemCount(): Int = listUser.size


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgUser)
        val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }
}