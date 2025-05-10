package com.example.voca.feature.home.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voca.R
import com.example.voca.ui.theme.PrimaryBlue
import com.example.voca.ui.theme.VocaTheme

@Composable
fun RecordButton(
    modifier: Modifier = Modifier,
    isRecording: Boolean = false,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .clip(CircleShape)
            .background(color = PrimaryBlue)
            .size(80.dp),
        onClick = onClick
    ) {
        AnimatedContent(
            targetState = isRecording,
            label = ""
        ) {isRecordingAnim ->
            if (isRecordingAnim){
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                    ,
                    painter = painterResource(
                        id = R.drawable.baseline_check_24
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
            }else{
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                    ,
                    painter = painterResource(
                        id = R.drawable.baseline_mic_24
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun RecordButtonPreview() {
    VocaTheme {
        RecordButton(
            onClick = {}
        )
    }
}