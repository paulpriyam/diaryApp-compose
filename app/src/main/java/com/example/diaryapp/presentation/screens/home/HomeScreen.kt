package com.example.diaryapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.diaryapp.presentation.components.HomeNavigationDrawer
import com.example.diaryapp.presentation.components.HomeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    onMenuItemClicked: () -> Unit,
    onSignOutClicked: () -> Unit,
    navigateToWriteScreen: () -> Unit
) {
    HomeNavigationDrawer(drawerState = drawerState, onSignOut = onSignOutClicked) {
        Scaffold(
            topBar = {
                HomeTopBar(onMenuItemClicked = onMenuItemClicked)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = navigateToWriteScreen) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "add diary")
                }
            },
            content = {

            })
    }

}