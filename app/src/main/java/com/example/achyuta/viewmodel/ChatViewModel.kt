package com.example.achyuta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achyuta.ai.GroqRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repository = GroqRepository()

    var response by mutableStateOf("")
        private set

    fun askGroq(prompt: String) {
        viewModelScope.launch {
            response = repository.askGroq(prompt)
        }
    }
}