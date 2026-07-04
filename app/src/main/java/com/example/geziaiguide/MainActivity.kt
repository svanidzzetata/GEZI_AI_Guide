package com.example.geziaiguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geziaiguide.data.local.AppDatabase
import com.example.geziaiguide.data.repository.PlacesRepository
import com.example.geziaiguide.ui.screens.MainContainer
import com.example.geziaiguide.ui.theme.GeziAIGuideTheme
import com.example.geziaiguide.ui.viewmodel.ChatViewModel
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    
    // Room Database და Repository ინიციალიზაცია
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { PlacesRepository(database.placeDao()) }

    // ViewModel-ების შექმნა Factory-ს მეშვეობით
    private val placesViewModel: PlacesViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PlacesViewModel(repository) as T
            }
        }
    }
    
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // ბაზის შევსება საწყისი მონაცემებით (თუ ცარიელია)
        placesViewModel.seedDatabase()

        setContent {
            GeziAIGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContainer(placesViewModel, chatViewModel)
                }
            }
        }
    }
}