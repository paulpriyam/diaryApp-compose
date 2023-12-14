package com.example.diaryapp.presentation.screens.auth

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AuthenticationScreen(
    loadingState: Boolean = false,
    onButtonClicked: () -> Unit = {}
) {
    Scaffold {
        AuthenticationComponent(
            loadingState = loadingState,
            onCLick = onButtonClicked
        )
    }
}