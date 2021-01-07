package com.devtech.mditest.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProductAndCart(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "cartOwnerId"
    )
    val cart: Cart
)