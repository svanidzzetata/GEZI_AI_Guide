package com.example.geziaiguide.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geziaiguide.data.model.Place
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(viewModel: PlacesViewModel, onChatClick: () -> Unit) {
    val places by viewModel.placesList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("📍 Gezi AI Guide", fontWeight = FontWeight.Bold) })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onChatClick() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("💬 Ask Gemini AI", color = Color.White)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(places) { place ->
                PlaceItem(place = place, onBookmarkClick = { viewModel.toggleBookmark(place) })
            }
        }
    }
}

@Composable
fun PlaceItem(place: Place, onBookmarkClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = place.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Region: ${place.region}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = place.description, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Rating: ${place.rating} / 5.0", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
            }

            Button(
                onClick = onBookmarkClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (place.isBookmarked) Color.Red else Color.Gray
                )
            ) {
                Text(if (place.isBookmarked) "Saved" else "Save", fontSize = 12.sp)
            }
        }
    }
}