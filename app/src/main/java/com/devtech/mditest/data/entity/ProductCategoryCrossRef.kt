package com.devtech.mditest.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["categoryId", "productId"])
data class ProductCategoryCrossRef(
    val categoryId: Long,
    val productId: Long
)