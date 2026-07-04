package com.example.geziaiguide.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(viewModel: PlacesViewModel) {
    val places by viewModel.placesList.collectAsState()
    val favoritePlaces = places.filter { it.isBookmarked }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("❤️ My Favorites", fontWeight = FontWeight.Bold) })
        }
    ) { paddingValues ->
        if (favoritePlaces.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No favorites yet!", fontSize = 18.sp, color = androidx.compose.ui.graphics.Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoritePlaces) { place ->
                    PlaceItem(place = place, onBookmarkClick = { viewModel.toggleBookmark(place) })
                }
            }
        }
    }
}