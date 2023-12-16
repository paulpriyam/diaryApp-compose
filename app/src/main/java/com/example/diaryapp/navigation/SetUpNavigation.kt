package com.example.diaryapp.navigation

import android.util.Log
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diaryapp.presentation.components.CustomAlertDialog
import com.example.diaryapp.presentation.screens.auth.AuthenticationScreen
import com.example.diaryapp.presentation.screens.home.HomeScreen
import com.example.diaryapp.viewmodel.AuthenticationViewModel
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.BuildConfig
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavigation(startDestination: String, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        authenticationRoute(navigateToHomeScreen = {
            navHostController.popBackStack()
            navHostController.navigate(Screens.HomeScreen.route)
        })
        homeRoute(navigateToWriteScreen = {
            navHostController.navigate(Screens.WriteScreen.route)
        }, navigateToAuthScreen = {
            navHostController.popBackStack()
            navHostController.navigate(Screens.AuthenticationScreen.route)
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

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeRoute(
    navigateToWriteScreen: () -> Unit,
    navigateToAuthScreen: () -> Unit
) {
    composable(route = Screens.HomeScreen.route) {
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val alertDialogState = remember {
            mutableStateOf(value = false)
        }
        HomeScreen(
            drawerState = drawerState,
            onMenuItemClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onSignOutClicked = {
                alertDialogState.value = true
            },
            navigateToWriteScreen = {
                navigateToWriteScreen.invoke()
            })

        CustomAlertDialog(
            title = "Sign Out",
            message = "Are you sure you want to sign out from your Google account",
            showAlertDialog = alertDialogState,
            onDialogClose = {
                alertDialogState.value = false
            },
            onYesClicked = {
                scope.launch(Dispatchers.IO) {
                    val user =
                        App.create(com.example.diaryapp.BuildConfig.MONGO_DB_API_KEY).currentUser
                    if (user != null) {
                        user.logOut()
                        withContext(Dispatchers.Main) {
                            navigateToAuthScreen.invoke()
                        }
                    }
                }
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