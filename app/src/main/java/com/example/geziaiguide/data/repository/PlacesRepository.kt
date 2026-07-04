package com.example.geziaiguide.data.repository

import com.example.geziaiguide.data.local.PlaceDao
import com.example.geziaiguide.data.model.Place
import kotlinx.coroutines.flow.Flow

class PlacesRepository(private val placeDao: PlaceDao) {

    // ყველა ადგილის წამოსაღებად Flow ბაზიდან
    val allPlaces: Flow<List<Place>> = placeDao.getAllPlaces()

    // ახალი ადგილის ჩასამატებლად
    suspend fun insertPlace(place: Place) {
        placeDao.insertPlace(place)
    }

    // ბუკმარკის სტატუსის განსაახლებლად
    suspend fun updateBookmark(id: Int, isBookmarked: Boolean) {
        placeDao.updateBookmark(id, isBookmarked)
    }
}