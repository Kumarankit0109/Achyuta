package com.example.achyuta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var recognizedText by mutableStateOf("")
        private set

    fun updateRecognizedText(text: String) {
        recognizedText = text
    }
}