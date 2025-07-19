package com.example.voca.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.voca.feature.auth.presentation.LoginScreen
import com.example.voca.feature.home.presentation.HomeScreen

@Composable
fun VocaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    innerPadding: PaddingValues,
    startDestination: Any
) {
    NavHost(
        modifier = modifier
            .padding(innerPadding),
        navController = navController,
        startDestination = startDestination
    ) {

        composable<LoginScreen> {
            LoginScreen()
        }

        composable<HomeScreen> {
            HomeScreen()
        }
    }
}