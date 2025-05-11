package com.example.voca.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.voca.feature.home.presentation.components.EmptyListScreen
import com.example.voca.feature.home.presentation.components.HomeTopBar
import com.example.voca.feature.home.presentation.components.RecorderSheetContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val homeViewModel = koinViewModel<HomeViewModel>()
    val state = homeViewModel.state.collectAsStateWithLifecycle()

    val blueWhiteGredient = listOf(
        Color(0xFFEBF1F9),
        Color(0xFF357AF5)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFEBF1F9),
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = blueWhiteGredient,
                        )
                    ),
                containerColor = Color.Transparent,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        EmptyListScreen(
            paddingValues = innerPadding
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
            ) {
                RecorderSheetContent(
                    onRecordAudio = {
                        homeViewModel.startRecording()
                    },
                    onStopRecording = {
                        homeViewModel.stopRecording()
                    },
                    onDiscardRecord = {
                        homeViewModel.discardRecord()
                    },
                    isRecording = state.value.isRecording
                )
            }
        }
    }
}