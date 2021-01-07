package com.devtech.mditest.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devtech.mditest.data.entity.Product
import com.devtech.mditest.databinding.ItemDetailBinding

class DetailAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Product, DetailAdapter.DetailViewHolder>(DiffCallback()) {
    private var selected = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }



    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val product = getItem(position)
                        listener.onItemClick(product)
                        selected = adapterPosition
                        notifyDataSetChanged()
                    }
                }
            }
        }

        fun bind(product: Product) {
            binding.apply {
                imageProduct.setImageResource(product.image)
            }
        }
    }



    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}