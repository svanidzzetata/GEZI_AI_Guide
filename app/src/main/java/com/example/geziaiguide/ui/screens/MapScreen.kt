package com.example.geziaiguide.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.geziaiguide.data.model.Place
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

import androidx.compose.ui.res.stringResource
import com.example.geziaiguide.R
import com.google.android.gms.maps.model.MapStyleOptions
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(viewModel: PlacesViewModel) {
    val context = LocalContext.current
    val places by viewModel.placesList.collectAsState()
    val tbilisi = LatLng(41.7151, 44.8271)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(tbilisi, 7f)
    }
    
    var selectedPlace by remember { mutableStateOf<Place?>(null) }

    val mapStyleOptions = remember {
        try {
            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
        } catch (e: Exception) {
            null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.explore_destinations), fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = false,
                    mapStyleOptions = mapStyleOptions
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = false
                ),
                onMapClick = { selectedPlace = null }
            ) {
                places.forEach { place ->
                    Marker(
                        state = MarkerState(position = LatLng(place.latitude, place.longitude)),
                        title = place.title,
                        onClick = {
                            selectedPlace = place
                            true
                        }
                    )
                }
            }

            selectedPlace?.let { place ->
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = place.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .width(120.dp)
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(place.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Text(place.region, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            Spacer(Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Star, null, tint = Color(0xFFFFB300), modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text(place.rating.toString(), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
