package com.devtech.mditest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devtech.mditest.R
import com.devtech.mditest.data.entity.Category
import com.devtech.mditest.databinding.ItemCategoryBinding
import com.devtech.mditest.utils.changeDrawableColor

class CategoryAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback()) {
    private var selected = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val category = getItem(position)
                        listener.onItemClick(category)
                        selected = adapterPosition
                        notifyDataSetChanged()
                    }
                }
            }
        }

        fun bind(category: Category) {
            binding.apply {
                name.text = category.name
                name.setCompoundDrawablesWithIntrinsicBounds(0, category.image, 0, 0)
                if(selected == adapterPosition){
                    materialCardView.setCardBackgroundColor(ContextCompat.getColor(root.context,R.color.colorPrimary))
                    name.setTextColor(ContextCompat.getColor(root.context,R.color.white))
                    name.changeDrawableColor(R.color.white)
                }else{
                    materialCardView.setCardBackgroundColor(ContextCompat.getColor(root.context,R.color.white))
                    name.setTextColor(ContextCompat.getColor(root.context,R.color.black))
                    name.changeDrawableColor(R.color.black)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem.categoryId == newItem.categoryId

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem == newItem
    }
}