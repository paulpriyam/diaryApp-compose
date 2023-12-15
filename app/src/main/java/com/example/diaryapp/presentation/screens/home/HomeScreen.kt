package com.example.diaryapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.diaryapp.presentation.components.HomeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(onMenuItemClicked: () -> Unit, navigateToWriteScreen: () -> Unit) {
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