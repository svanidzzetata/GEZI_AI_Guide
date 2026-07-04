package com.example.geziaiguide.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.geziaiguide.R
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: PlacesViewModel, onFavoritesClick: () -> Unit) {
    val places by viewModel.placesList.collectAsState()
    val bookmarkedCount = places.count { it.isBookmarked }
    
    val defaultName = stringResource(R.string.default_user_name)
    val defaultBio = stringResource(R.string.default_bio)
    
    var name by remember { mutableStateOf(defaultName) }
    var bio by remember { mutableStateOf(defaultBio) }
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.my_profile), fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { isEditing = !isEditing }) {
                        Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.edit_profile_desc))
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            // Existing Basic User Info Section
            item {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://ui-avatars.com/api/?name=Tamar+Svanidze&background=0D8ABC&color=fff&size=256",
                        contentDescription = stringResource(R.string.profile_picture),
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(16.dp))
                    
                    if (isEditing) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(stringResource(R.string.name_label)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = bio,
                            onValueChange = { bio = it },
                            label = { Text(stringResource(R.string.bio_label)) },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 4
                        )
                        Button(
                            onClick = { isEditing = false },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text(stringResource(R.string.save_changes))
                        }
                    } else {
                        Text(
                            name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            stringResource(R.string.explorer_level_pro),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            bio,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Existing Quick Stats Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { onFavoritesClick() }
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text(bookmarkedCount.toString(), fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                        Text(stringResource(R.string.favorites), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    ProfileStat(label = stringResource(R.string.visited), value = "12", icon = Icons.Default.Map)
                    ProfileStat(label = stringResource(R.string.reviews), value = "8", icon = Icons.Default.TravelExplore)
                }
            }

            // Existing Travel Progress Card
            item {
                Spacer(Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(stringResource(R.string.travel_stats), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(12.dp))
                        LinearProgressIndicator(
                            progress = { 0.65f },
                            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape)
                        )
                        Text(
                            stringResource(R.string.explored_percentage),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            // --- ENHANCED PROFILE DASHBOARD SECTION ---
            
            // 1. Top Dashboard Header
            item {
                Spacer(Modifier.height(40.dp))
                DashboardHeader()
            }

            // 2. Analytics Cards Grid
            item {
                Spacer(Modifier.height(24.dp))
                AnalyticsGrid(bookmarkedCount)
            }

            // 3. Recommended Itineraries Section
            item {
                Spacer(Modifier.height(40.dp))
                ItinerariesSection()
            }
        }
    }
}

@Composable
fun DashboardHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "PROFILE DASHBOARD",
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF2563EB),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Welcome back, Traveler.",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = "Routes auto-generated from weather, opening hours, and your live location.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            
            // Live Weather Widget
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f)),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Today in Tbilisi", fontSize = 10.sp, color = Color.Gray)
                        Text("22°C • Sunny", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    }
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun AnalyticsGrid(savedPlaces: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        AnalyticsCard(Icons.Default.Route, Color(0xFF2563EB), "12", "Routes generated")
        AnalyticsCard(Icons.Default.Favorite, Color(0xFFF59E0B), savedPlaces.toString(), "Saved places")
        AnalyticsCard(Icons.Default.CalendarToday, Color(0xFF0F172A), "4d", "Trip remaining")
        AnalyticsCard(Icons.Default.AutoAwesome, Color(0xFF60A5FA), "47", "AI conversations")
    }
}

@Composable
fun AnalyticsCard(icon: ImageVector, iconColor: Color, stat: String, label: String) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.height(16.dp))
            Text(stat, fontWeight = FontWeight.Black, fontSize = 24.sp, color = Color(0xFF0F172A))
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        }
    }
}

@Composable
fun ItinerariesSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Recommended Itineraries",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF0F172A),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            ItineraryCard(
                title = "Old Tbilisi Heritage Walk",
                tags = listOf("Historic" to Icons.Default.History, "4h" to Icons.Default.AccessTime),
                items = listOf(
                    TimelineItem("Narikala Fortress", "Open 24h", "https://images.unsplash.com/photo-1565008447742-97f6f38c985c?w=200"),
                    TimelineItem("Georgian National Museum", "10:00 - 18:00 (Closed Mon)", "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=200"),
                    TimelineItem("Svetitskhoveli Cathedral", "08:00 - 20:00", "https://images.unsplash.com/photo-1527269534026-c86f4009eace?w=200")
                )
            )
            
            ItineraryCard(
                title = "Caucasus Mountain Escape",
                tags = listOf("Adventure" to Icons.Default.Landscape, "Full day" to Icons.Default.AccessTime),
                items = listOf(
                    TimelineItem("Gergeti Trinity Church", "Open 24h", "https://images.unsplash.com/photo-1589308078059-be1415eab4c3?w=200"),
                    TimelineItem("Ananuri Castle Complex", "09:00 - 18:00", "https://images.unsplash.com/photo-1523585962828-aac6d073bd7b?w=200")
                ),
                showButton = true
            )

            ItineraryCard(
                title = "Wine & Wild Coast",
                tags = listOf("Romantic" to Icons.Default.Favorite, "2 days" to Icons.Default.AccessTime),
                items = listOf(
                    TimelineItem("Sighnaghi Old Town", "Always open", "https://images.unsplash.com/photo-1561542320-9a18cd340469?w=200"),
                    TimelineItem("Svan Towers, Mestia", "Always open", "https://images.unsplash.com/photo-1589308078059-be1415eab4c3?w=200")
                ),
                showButton = true
            )
        }
    }
}

data class TimelineItem(val title: String, val hours: String, val imageUrl: String)

@Composable
fun ItineraryCard(title: String, tags: List<Pair<String, ImageVector>>, items: List<TimelineItem>, showButton: Boolean = false) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(end = 20.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                tags.forEach { (text, icon) ->
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(icon, null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                            Spacer(Modifier.width(4.dp))
                            Text(text, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
            Spacer(Modifier.height(16.dp))
            
            items.forEachIndexed { index, item ->
                Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2563EB)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text((index + 1).toString(), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        if (index < items.size - 1) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .fillMaxHeight()
                                    .background(Color(0xFF2563EB).copy(alpha = 0.2f))
                            )
                        }
                    }
                    Spacer(Modifier.width(16.dp))
                    Row(modifier = Modifier.padding(bottom = 16.dp)) {
                        AsyncImage(
                            model = item.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(item.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF0F172A))
                            Text(item.hours, fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                }
            }

            if (showButton) {
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Open in AI Guide", fontSize = 12.sp)
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, null, modifier = Modifier.size(14.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileStat(label: String, value: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
