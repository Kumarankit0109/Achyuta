package com.example.achyuta.model

data class AssistantUiState(
    val state: AssistantState = AssistantState.Idle,
    val transcript: String = "",
    val response: String = ""
)