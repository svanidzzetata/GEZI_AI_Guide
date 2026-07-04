package com.example.geziaiguide.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.geziaiguide.ui.viewmodel.ChatViewModel
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel

@Composable
fun MainContainer(
    placesViewModel: PlacesViewModel, 
    chatViewModel: ChatViewModel,
    isDarkMode: Boolean,
    onThemeToggle: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    
    val items = listOf("Discover", "Map", "AI Guide", "Favorites")
    val routes = listOf("discover", "map", "chat", "favorites")
    val icons = listOf<ImageVector>(
        Icons.Filled.Explore,
        Icons.Filled.Map,
        Icons.Filled.AutoAwesome,
        Icons.Filled.Favorite
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                items.forEachIndexed { index, item ->
                    val route = routes[index]
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = currentDestination == route,
                        onClick = {
                            if (currentDestination != route) {
                                navController.navigate(route) {
                                    popUpTo("discover") { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "discover",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("discover") {
                PlacesScreen(
                    viewModel = placesViewModel, 
                    isDarkMode = isDarkMode,
                    onThemeToggle = onThemeToggle,
                    onChatClick = {
                        navController.navigate("chat") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable("map") {
                MapScreen(placesViewModel)
            }
            composable("chat") {
                ChatScreen(chatViewModel, onBackClick = {
                    navController.popBackStack()
                })
            }
            composable("favorites") {
                FavoritesScreen(placesViewModel)
            }
        }
    }
}