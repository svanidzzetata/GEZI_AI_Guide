package com.example.geziaiguide.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val placeId: Int,
    val userName: String,
    val userAvatar: String,
    val commentText: String,
    val rating: Int,
    val date: String
)