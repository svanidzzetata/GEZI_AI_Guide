package com.example.geziaiguide.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geziaiguide.data.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// დროებით ამოვიღეთ კონსტრუქტორიდან რეპოზიტორია, რომ MainActivity-მ ჩვეულებრივად შექმნას
class PlacesViewModel : ViewModel() {

    private val _placesList = MutableStateFlow<List<Place>>(emptyList())
    val placesList: StateFlow<List<Place>> = _placesList.asStateFlow()

    init {
        loadDummyPlaces()
    }

    private fun loadDummyPlaces() {
        // პირდაპირ ვაწვდით ადგილებს, ბაზის გარეშე, რომ 1 წამში აღარ გამოირთოს!
        _placesList.value = listOf(
            Place(id = 1, title = "Tbilisi Old Town", region = "Tbilisi", description = "Historic district with sulfur baths and narrow streets.", rating = 4.8, isBookmarked = false, imageUrl = ""),
            Place(id = 2, title = "Kazbegi - Gergeti", region = "Mtskheta-Mtianeti", description = "Iconic Trinity Church under Mount Kazbek.", rating = 4.9, isBookmarked = false, imageUrl = ""),
            Place(id = 3, title = "Mestia & Ushguli", region = "Samegrelo-Zemo Svaneti", description = "Ancient defensive towers and Caucasus mountains.", rating = 5.0, isBookmarked = false, imageUrl = "")
        )
    }

    fun toggleBookmark(place: Place) {
        viewModelScope.launch {
            // დროებითი ბუკმარკის ლოგიკა კოდშივე, ბაზის გარეშე
            _placesList.value = _placesList.value.map {
                if (it.id == place.id) it.copy(isBookmarked = !it.isBookmarked) else it
            }
        }
    }
}