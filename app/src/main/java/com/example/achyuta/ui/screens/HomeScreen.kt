package com.example.achyuta.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.achyuta.ui.components.AppHeader
import com.example.achyuta.ui.components.BottomNavigationBar
import com.example.achyuta.ui.components.MicrophoneButton
import com.example.achyuta.ui.components.QuoteCard
import com.example.achyuta.ui.components.StatusCard
import com.example.achyuta.ui.theme.Gold

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    recognizedText: String = "",
    aiResponse: String = "",
    onMicClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            AppHeader()

            Spacer(modifier = Modifier.height(16.dp))

            MicrophoneButton(
                onMicClick = onMicClick
            )

            Spacer(modifier = Modifier.height(20.dp))

            StatusCard()

            Spacer(modifier = Modifier.height(20.dp))

            QuoteCard()

            Spacer(modifier = Modifier.height(24.dp))

            if (recognizedText.isNotBlank()) {
                Text(
                    text = "You said:",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = recognizedText,
                    color = Gold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (aiResponse.isNotBlank()) {
                Text(
                    text = "Achyuta:",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = aiResponse,
                    color = Gold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            BottomNavigationBar()
        }
    }
}