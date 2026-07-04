package com.example.geziaiguide.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _chatHistory = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val chatHistory: StateFlow<List<Pair<String, Boolean>>> = _chatHistory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyD_YOUR_KEY_HERE" // შეცვალეთ თქვენი რეალური გასაღებით
    )

    fun sendMessage(userMessage: String) {
        if (userMessage.isBlank()) return

        val currentList = _chatHistory.value.toMutableList()
        currentList.add(Pair(userMessage, true))
        _chatHistory.value = currentList
        
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(userMessage)
                val updatedList = _chatHistory.value.toMutableList()
                updatedList.add(Pair(response.text ?: "AI could not generate a response.", false))
                _chatHistory.value = updatedList
            } catch (e: Exception) {
                val updatedList = _chatHistory.value.toMutableList()
                updatedList.add(Pair("Error: ${e.localizedMessage}", false))
                _chatHistory.value = updatedList
            } finally {
                _isLoading.value = false
            }
        }
    }
}