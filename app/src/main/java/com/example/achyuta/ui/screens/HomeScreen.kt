package com.example.achyuta.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achyuta.model.AssistantState
import com.example.achyuta.model.AssistantUiState
import com.example.achyuta.ui.components.AppHeader
import com.example.achyuta.ui.components.MicrophoneButton
import com.example.achyuta.ui.theme.Gold

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: AssistantUiState,
    onMicClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppHeader()

        Spacer(modifier = Modifier.height(32.dp))

        MicrophoneButton(
            onMicClick = onMicClick
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = when (uiState.state) {
                AssistantState.Idle -> "Tap the microphone"
                AssistantState.Listening -> "Listening..."
                AssistantState.Thinking -> "Thinking..."
                AssistantState.Speaking -> "Speaking..."
            },
            color = Gold,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2A2A2A)
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "You said",
                    color = Gold,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (uiState.transcript.isBlank())
                        "Waiting..."
                    else
                        uiState.transcript,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2A2A2A)
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Achyuta",
                    color = Gold,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (uiState.response.isBlank())
                        "Awaiting response..."
                    else
                        uiState.response,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}