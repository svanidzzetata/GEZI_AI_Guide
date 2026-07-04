package com.example.geziaiguide.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class Place(
    @PrimaryKey val id: Int,
    val title: String,
    val region: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val isBookmarked: Boolean = false
)