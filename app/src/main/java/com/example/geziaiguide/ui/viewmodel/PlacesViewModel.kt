package com.example.geziaiguide.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geziaiguide.data.model.Place
import com.example.geziaiguide.data.repository.PlacesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlacesViewModel(private val repository: PlacesRepository) : ViewModel() {

    // Flow-ს გარდაქმნა StateFlow-ში, რომ UI-მ ყოველთვის აქტუალური მონაცემები ნახოს
    val placesList: StateFlow<List<Place>> = repository.allPlaces
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleBookmark(place: Place) {
        viewModelScope.launch {
            repository.updateBookmark(place.id, !place.isBookmarked)
        }
    }

    // თავდაპირველი მონაცემების შევსება, თუ ბაზა ცარიელია
    fun seedDatabase() {
        viewModelScope.launch {
            // ვიყენებთ first()-ს რომ რეალურად შევამოწმოთ ბაზის მდგომარეობა
            val currentPlaces = repository.allPlaces.first()
            if (currentPlaces.isEmpty()) {
                val dummyPlaces = listOf(
                    Place(1, "Old Tbilisi", "Tbilisi", "Sulfur baths and narrow streets.", "https://images.unsplash.com/photo-1565008447742-97f6f38c985c?auto=format&fit=crop&w=800", 4.8, 41.6881, 44.8091),
                    Place(2, "Gergeti Trinity", "Kazbegi", "Church under Mount Kazbek.", "https://images.unsplash.com/photo-1527269534026-c86f4009eace?auto=format&fit=crop&w=800", 4.9, 42.6625, 44.6201),
                    Place(3, "Ushguli", "Svaneti", "Highest inhabited village in Europe.", "https://images.unsplash.com/photo-1589308078059-be1415eab4c3?auto=format&fit=crop&w=800", 5.0, 42.9172, 43.0152),
                    Place(4, "Batumi Boulevard", "Adjara", "Modern seaside city.", "https://images.unsplash.com/photo-1612371534015-7790b0798e8f?auto=format&fit=crop&w=800", 4.7, 41.6493, 41.6249),
                    Place(5, "Martvili Canyon", "Samegrelo", "Turquoise water and waterfalls.", "https://images.unsplash.com/photo-1565967511849-76a60a516170?auto=format&fit=crop&w=800", 4.9, 42.4575, 42.3776)
                )
                dummyPlaces.forEach { repository.insertPlace(it) }
            }
        }
    }
}