package com.devtech.mditest.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cart_table",indices = [Index(value = arrayOf("cartOwnerId"), unique = true)])
@Parcelize
data class Cart(
    val cartOwnerId: Long,
    val quantity: Long,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val cartId: Long = 0
) : Parcelable