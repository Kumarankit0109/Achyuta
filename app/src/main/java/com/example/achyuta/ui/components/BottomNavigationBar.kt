package com.example.achyuta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achyuta.ui.theme.CardBackground
import com.example.achyuta.ui.theme.Gold
import com.example.achyuta.ui.theme.SecondaryText

@Composable
fun BottomNavigationBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 14.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NavItem(
            icon = "🏠",
            title = "Home",
            selected = true
        )

        NavItem(
            icon = "💬",
            title = "Chat"
        )

        NavItem(
            icon = "🧠",
            title = "Memory"
        )

        NavItem(
            icon = "⚙️",
            title = "Settings"
        )
    }
}

@Composable
fun NavItem(
    icon: String,
    title: String,
    selected: Boolean = false
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = icon,
            fontSize = 22.sp
        )

        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) Gold else SecondaryText
        )

    }
}