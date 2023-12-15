package com.example.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.navigation.Screens
import com.example.diaryapp.navigation.SetUpNavigation
import com.example.diaryapp.ui.theme.DiaryAppTheme
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            DiaryAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SetUpNavigation(
                    startDestination = getStartDestination(),
                    navHostController = navController
                )
            }
        }
    }

    private fun getStartDestination(): String {
        val user = App.create(BuildConfig.MONGO_DB_API_KEY).currentUser
        return if (user != null && user.loggedIn) Screens.HomeScreen.route
        else Screens.AuthenticationScreen.route
    }
}