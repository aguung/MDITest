package com.devtech.mditest.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProductAndFavorite(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "userOwnerId"
    )
    val favorite: Favorite
)
