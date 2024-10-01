package com.ndridm.sportcars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ndridm.sportcars.databinding.ItemRowCarBinding

class ListCarsAdapter(
    private val listCar: ArrayList<Cars>
): RecyclerView.Adapter<ListCarsAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowCarBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowCarBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCar.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, rating, photo) = listCar[position]
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.binding.imgItemPhoto) // imageView mana yang akan diterapkan
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.binding.tvItemRating.text = rating

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listCar[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Cars)
    }
}