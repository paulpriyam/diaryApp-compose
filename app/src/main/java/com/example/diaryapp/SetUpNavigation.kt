package com.example.diaryapp

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diaryapp.navigation.Screens

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