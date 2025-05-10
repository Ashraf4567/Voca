package com.example.voca.feature.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voca.R
import kotlinx.coroutines.delay

@Composable
fun EmptyListScreen (
    paddingValues: PaddingValues,
){

    var showVector by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showTopic by remember { mutableStateOf(false) }
    val animatedAlpha by animateFloatAsState(
        targetValue = if (showTopic) 1.0f else 0f,
        label = "alpha",
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(Unit) {
        delay(500)
        showVector = true
        delay(500)
        showTitle = true
        delay(500)
        showTopic = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = showVector,
                enter = fadeIn() + expandVertically(
                    animationSpec = tween(
                        durationMillis = 2000,
                    )
                )
            ) {
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedVisibility(
                visible = showTitle
            ) {
                Text(
                    text = "Start Your Journey",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = animatedAlpha
                    },
                text = "Every big step start with small step.\n" +
                        "Notes your first idea and start\n" +
                        "your journey!",
                color = Color.Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )


        }
    }
}
