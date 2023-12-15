package com.example.diaryapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diaryapp.presentation.screens.auth.AuthenticationScreen
import com.example.diaryapp.presentation.screens.home.HomeScreen
import com.example.diaryapp.viewmodel.AuthenticationViewModel
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SetUpNavigation(startDestination: String, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        authenticationRoute(navigateToHomeScreen = {
            navHostController.popBackStack()
            navHostController.navigate(Screens.HomeScreen.route)
        })
        homeRoute(navigateToWriteScreen = {
            navHostController.navigate(Screens.WriteScreen.route)
        })
        writeRoute()
    }
}

fun NavGraphBuilder.authenticationRoute(navigateToHomeScreen: () -> Unit) {
    composable(route = Screens.AuthenticationScreen.route) {
        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()
        val viewModel: AuthenticationViewModel = viewModel()
        val loadingState = viewModel.loadingState.value
        val authenticateState = viewModel.authenticated.value
        AuthenticationScreen(
            authenticateState,
            loadingState = loadingState,
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
                    }
                    viewModel.setLoadingState(false)
                }, onError = {
                    messageBarState.addError(it)
                    viewModel.setLoadingState(loading = false)
                })
            },
            onDialogDismissed = {
                messageBarState.addError(exception = it)
                viewModel.setLoadingState(false)
            },
            navigateToHomeScreen = {
                navigateToHomeScreen.invoke()
            }
        )
    }
}

fun NavGraphBuilder.homeRoute(navigateToWriteScreen: () -> Unit) {
    composable(route = Screens.HomeScreen.route) {
       HomeScreen(onMenuItemClicked = {}, navigateToWriteScreen = {

       })
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