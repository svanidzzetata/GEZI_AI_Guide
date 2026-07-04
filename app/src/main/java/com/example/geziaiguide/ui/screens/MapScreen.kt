package com.example.geziaiguide.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(viewModel: PlacesViewModel) {
    val places by viewModel.placesList.collectAsState()
    val tbilisi = LatLng(41.7151, 44.8271)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(tbilisi, 7f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Travel Map", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false,
                mapStyleOptions = null // Could add custom styling here
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            )
        ) {
            places.forEach { place ->
                Marker(
                    state = MarkerState(position = LatLng(place.latitude, place.longitude)),
                    title = place.title,
                    snippet = place.region
                )
            }
        }
    }
}