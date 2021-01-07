package com.devtech.mditest.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Entity(tableName = "product_table")
@Parcelize
data class Product(
    val name: String,
    val image: Int,
    val description: String,
    val price: Long,
    val quantity: Long,
    val color: List<Int>,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val productId: Long = 0
) : Parcelable