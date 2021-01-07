package com.devtech.mditest.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_table")
@Parcelize
data class Favorite(
    val favoriteOwnerId: Long,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val favoriteId: Long = 0
) : Parcelable