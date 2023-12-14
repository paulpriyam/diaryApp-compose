package com.example.diaryapp

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryapp.navigation.Screens
import com.example.diaryapp.presentation.screens.auth.AuthenticationScreen
import com.example.diaryapp.viewmodel.AuthenticationViewModel
import com.stevdzasan.messagebar.rememberMessageBarState
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
        val messageBarState = rememberMessageBarState()
        val viewModel: AuthenticationViewModel = viewModel()
        AuthenticationScreen(
            loadingState = viewModel.loadingState.value,
            oneTapState = oneTapState,
            messageBarState = messageBarState,
            onButtonClicked = {
                viewModel.setLoadingState(true)
                oneTapState.open()
            },
            onTokenReceived = { token ->
                Log.d("TAG", "authenticationRoute: $token")
                viewModel.signInWithMongoAtlas(tokenId = token, onSuccess = {
                    if (it) {
                        messageBarState.addSuccess("Successfully Signed in")
                        viewModel.setLoadingState(false)
                    }
                }, onError = {
                    messageBarState.addError(it)
                })
            },
            onDialogDismissed = {
                messageBarState.addError(exception = it)
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