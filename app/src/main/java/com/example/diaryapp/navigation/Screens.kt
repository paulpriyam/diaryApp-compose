package com.example.diaryapp.navigation

sealed class Screens(val route: String) {
    object AuthenticationScreen : Screens(route = "authentication_screen")
    object HomeScreen : Screens(route = "home_screen")
    object WriteScreen : Screens(route = "write_screen?diaryId={diaryId}") {
        fun toRoute(diaryId: String): String {
            return "write_screen?diaryId=$diaryId"
        }
    }
}