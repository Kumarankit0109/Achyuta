package com.example.achyuta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achyuta.ui.theme.CardBackground
import com.example.achyuta.ui.theme.Gold
import com.example.achyuta.ui.theme.WhiteText

@Composable
fun QuoteCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Always remember Krishna.\nNever forget Krishna.",
            color = WhiteText,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}