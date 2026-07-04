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

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyD_YOUR_KEY_HERE" // აქ მოგვიანებით ნამდვილ კლავიშს ჩავსვამთ
    )

    fun sendMessage(userMessage: String) {
        if (userMessage.isBlank()) return

        val currentList = _chatHistory.value.toMutableList()
        currentList.add(Pair(userMessage, true))
        _chatHistory.value = currentList

        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(userMessage)
                val updatedList = _chatHistory.value.toMutableList()
                updatedList.add(Pair(response.text ?: "AI-მ პასუხი ვერ დააგენერირა.", false))
                _chatHistory.value = updatedList
            } catch (e: Exception) {
                val updatedList = _chatHistory.value.toMutableList()
                updatedList.add(Pair("შეცდომა: ${e.localizedMessage}", false))
                _chatHistory.value = updatedList
            }
        }
    }
}