package com.example.geziaiguide.data.local

import androidx.room.*
import com.example.geziaiguide.data.model.Comment
import com.example.geziaiguide.data.model.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM places")
    fun getAllPlaces(): Flow<List<Place>>

    @Query("SELECT * FROM places WHERE id = :id")
    fun getPlaceById(id: Int): Flow<Place?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: Place)

    @Query("UPDATE places SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmark(id: Int, isBookmarked: Boolean)

    @Query("SELECT * FROM comments WHERE placeId = :placeId")
    fun getCommentsForPlace(placeId: Int): Flow<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment)
}