package com.example.voca.feature.home.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voca.ui.theme.VocaTheme
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun RecorderSheetContent(
    modifier: Modifier = Modifier,
    isRecording: Boolean = false,
    onRecordAudio: () -> Unit,
    onStopRecording: () -> Unit,
    onDiscardRecord: () -> Unit
) {

    var timeMillis by remember { mutableStateOf(0L) }

    LaunchedEffect(isRecording) {
        if (isRecording) {
            val startTime = System.currentTimeMillis()
            while (isRecording) {
                timeMillis = System.currentTimeMillis() - startTime
                delay(100)
            }
        } else {
            timeMillis = 0L
        }
    }

    val minutes = (timeMillis / 60000).toInt()
    val seconds = ((timeMillis % 60000) / 1000).toInt()
    val hundredths = ((timeMillis % 1000) / 10).toInt()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 20.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = isRecording,
            label = "",
            transitionSpec = {
                slideInVertically { it } + fadeIn() togetherWith
                        slideOutVertically { -it } + fadeOut()
            }
        ) { isRecordingAnimated ->
            Text(
                text = if (isRecordingAnimated) "Recording your memories..." else "Tap to record your memories",
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = String.format("%02d:%02d:%02d", minutes, seconds, hundredths),
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AnimatedContent(
                targetState = isRecording,
                label = "",
                transitionSpec = {
                    // scale out in animation
                    scaleIn(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ) + fadeIn() togetherWith
                            scaleOut() + fadeOut()
                }
            ) { isRecordingAnim->
                if (isRecordingAnim) {
                    CloseIcon { onDiscardRecord() }
                } else {
                    // make a place holder same size
                    Spacer(modifier.size(60.dp))
                }
            }


            RecordButton(
                isRecording = isRecording,
                onClick = {
                    if (isRecording) {
                        onStopRecording()
                    } else {
                        onRecordAudio()
                    }
                }
            )

            PauseIcon {  }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun RecorderSheetContentPreview() {
    VocaTheme {
        RecorderSheetContent(
            isRecording = true,
            onRecordAudio = {},
            onStopRecording = {},
            onDiscardRecord = {}
        )
    }
}