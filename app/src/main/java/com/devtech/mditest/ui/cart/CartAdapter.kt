package com.devtech.mditest.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devtech.mditest.data.entity.ProductAndCart
import com.devtech.mditest.databinding.ItemCartBinding

class CartAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ProductAndCart, CartAdapter.CartViewHolder>(DiffCallback()) {
    private var selected = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) :
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

        fun bind(productAndCart: ProductAndCart) {
            binding.apply {
                txtPrice.text = productAndCart.product.price.toString()
                txtDescription.text = productAndCart.product.description
                txtProduct.text = productAndCart.product.name
                txtQuatinty.text = productAndCart.cart?.quantity.toString()
            }
        }
    }



    interface OnItemClickListener {
        fun onItemClick(productAndCart: ProductAndCart)
    }

    class DiffCallback : DiffUtil.ItemCallback<ProductAndCart>() {
        override fun areItemsTheSame(oldItem: ProductAndCart, newItem: ProductAndCart) =
            oldItem.product.productId == newItem.product.productId

        override fun areContentsTheSame(oldItem: ProductAndCart, newItem: ProductAndCart) =
            oldItem == newItem
    }
}