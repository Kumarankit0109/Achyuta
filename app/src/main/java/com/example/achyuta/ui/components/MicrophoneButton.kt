package com.example.achyuta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achyuta.ui.theme.Gold
import com.example.achyuta.ui.theme.WhiteText

@Composable
fun MicrophoneButton(
    onMicClick: () -> Unit
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier
                .size(120.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = CircleShape
                )
                .clickable {
                    onMicClick()
                },
            shape = CircleShape,
            color = Gold
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "🎤",
                    fontSize = 80.sp
                )

            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tap to Speak",
            color = WhiteText,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

    }

}