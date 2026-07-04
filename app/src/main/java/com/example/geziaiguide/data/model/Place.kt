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
    val latitude: Double = 41.7151, // Default to Tbilisi coordinates
    val longitude: Double = 44.8271,
    val isBookmarked: Boolean = false
)