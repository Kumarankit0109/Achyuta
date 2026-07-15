package com.example.achyuta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF071B2F))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "ACHYUTA",
                color = Color(0xFFFFD54F),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Hare Krishna 🙏",
                color = Color.White,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your Personal AI Assistant",
                color = Color.LightGray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1565C0)
                )
            ) {
                Text(
                    text = "🎤 Listen",
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Status : Ready",
                color = Color(0xFF81C784),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Version 0.1",
                color = Color.Gray
            )
        }
    }
}