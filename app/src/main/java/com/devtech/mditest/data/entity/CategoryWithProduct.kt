package com.devtech.mditest.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithProduct(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "productId",
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val product: List<Product>
)