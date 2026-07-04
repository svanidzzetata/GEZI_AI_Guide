package com.example.geziaiguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.geziaiguide.ui.screens.PlacesScreen
import com.example.geziaiguide.ui.theme.GeziAIGuideTheme
import com.example.geziaiguide.ui.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // დროებით ამოვიღეთ Room ბაზის ინიციალიზაცია, რომ არ გამოირთოს!
        // PlacesViewModel-ს გადავცემთ Dummy (ცარიელ) ვერსიას, თუ შენი კონსტრუქტორი ამის საშუალებას იძლევა.
        // თუ მაინც მოითხოვს რეპოზიტორიას, აპლიკაცია რომ ჩაირთოს, უბრალოდ შევქმნათ მარტივი ViewModel:
        val viewModel = PlacesViewModel()

        setContent {
            GeziAIGuideTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlacesScreen(
                        viewModel = viewModel,
                        onChatClick = { /* ჯერჯერობით ცარიელია */ }
                    )
                }
            }
        }
    }
}