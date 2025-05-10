package com.example.voca.feature.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voca.ui.theme.VocaTheme

@Composable
fun CloseIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .clip(CircleShape)
            .background(color = Color.Red.copy(alpha = .1f))
            .size(60.dp),
        onClick = onClick

    ) {
        Icon(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
                ,
            imageVector = Icons.Rounded.Close,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CloseIconPreview() {
    VocaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CloseIcon(
                onClick = {}
            )
        }

    }
}