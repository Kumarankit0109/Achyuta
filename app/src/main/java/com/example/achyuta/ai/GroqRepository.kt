package com.example.achyuta.ai

class GroqRepository {

    suspend fun askGroq(prompt: String): String {

        return try {

            val response = RetrofitClient.api.chat(
                ChatRequest(
                    model = "llama-3.3-70b-versatile",
                    messages = listOf(
                        Message(
                            role = "user",
                            content = prompt
                        )
                    )
                )
            )

            response.choices.firstOrNull()?.message?.content
                ?: "No response received."

        } catch (e: Exception) {

            "Error: ${e.message}"

        }
    }
}