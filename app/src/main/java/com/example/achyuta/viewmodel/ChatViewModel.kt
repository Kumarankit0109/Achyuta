package com.example.achyuta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achyuta.ai.GroqRepository
import com.example.achyuta.model.AssistantState
import com.example.achyuta.model.AssistantUiState
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repository = GroqRepository()

    var uiState by mutableStateOf(AssistantUiState())
        private set

    /**
     * Called before opening Speech Recognition
     */
    fun setListening() {
        uiState = uiState.copy(
            state = AssistantState.Listening,
            transcript = "",
            response = ""
        )
    }

    /**
     * Called after speech recognition finishes
     */
    fun askGroq(prompt: String) {

        val text = prompt.lowercase().trim()

        // Custom responses about Tachyon
        if (
            text.contains("who created you") ||
            text.contains("who made you") ||
            text.contains("who is your creator") ||
            text.contains("who is your founder") ||
            text.contains("who founded you") ||
            text.contains("who developed you") ||
            text.contains("who is your developer") ||
            text.contains("who is your ceo")
        ) {

            uiState = uiState.copy(
                state = AssistantState.Speaking,
                transcript = prompt,
                response = "I am Achyuta, created by Kumar Ankit, He is my developer and creator."
            )

            return
        }

        uiState = uiState.copy(
            state = AssistantState.Thinking,
            transcript = prompt,
            response = ""
        )

        viewModelScope.launch {

            try {

                val answer = repository.askGroq(prompt)

                uiState = uiState.copy(
                    state = AssistantState.Speaking,
                    response = answer
                )

            } catch (e: Exception) {

                uiState = uiState.copy(
                    state = AssistantState.Idle,
                    response = "Sorry, something went wrong."
                )
            }
        }
    }

    /**
     * Called when Text-To-Speech starts
     */
    fun speakingStarted() {

        uiState = uiState.copy(
            state = AssistantState.Speaking
        )
    }

    /**
     * Called when Text-To-Speech finishes
     */
    fun speakingFinished() {

        uiState = uiState.copy(
            state = AssistantState.Idle
        )
    }

    /**
     * Stop everything and return to idle
     */
    fun reset() {

        uiState = AssistantUiState()
    }
}