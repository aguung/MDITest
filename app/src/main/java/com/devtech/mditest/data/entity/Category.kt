package com.devtech.mditest.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category_table")
@Parcelize
data class Category(
    val name: String,
    val image: Int,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val categoryId: Long = 0
) : Parcelable