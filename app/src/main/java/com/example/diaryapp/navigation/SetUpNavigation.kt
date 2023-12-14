package com.example.diaryapp

import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryapp.navigation.Screens
import com.example.diaryapp.presentation.screens.auth.AuthenticationScreen
import com.stevdzasan.onetap.rememberOneTapSignInState
import kotlinx.coroutines.delay
import java.util.logging.Handler

@Composable
fun SetUpNavigation(startDestination: String, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        authenticationRoute()
        homeRoute()
        writeRoute()
    }
}

fun NavGraphBuilder.authenticationRoute() {
    composable(route = Screens.AuthenticationScreen.route) {
        val oneTapState = rememberOneTapSignInState()
        AuthenticationScreen(
            loadingState = oneTapState.opened,
            oneTapState = oneTapState,
            onButtonClicked = {
                oneTapState.open()
            }
        )
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screens.HomeScreen.route) {

    }
}

fun NavGraphBuilder.writeRoute() {
    composable(
        route = Screens.WriteScreen.route,
        arguments = listOf(navArgument(name = "write_screen") {
            type = NavType.StringType
            nullable = true
        })
    ) {

    }
}