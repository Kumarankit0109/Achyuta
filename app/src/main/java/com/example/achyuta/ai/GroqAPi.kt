package com.example.achyuta.ai

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GroqApi {

    @Headers("Content-Type: application/json")
    @POST("openai/v1/chat/completions")
    suspend fun chat(
        @Body request: ChatRequest
    ): ChatResponse
}