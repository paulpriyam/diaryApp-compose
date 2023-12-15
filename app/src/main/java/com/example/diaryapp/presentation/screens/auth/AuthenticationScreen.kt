package com.example.diaryapp.presentation.screens.auth

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.diaryapp.BuildConfig
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    authenticateState: Boolean,
    loadingState: Boolean = false,
    oneTapState: OneTapSignInState,
    messageBarState: MessageBarState,
    onTokenReceived: (String) -> Unit,
    onDialogDismissed: (Exception) -> Unit,
    onButtonClicked: () -> Unit = {},
    navigateToHomeScreen:()->Unit
) {
    Scaffold {
        ContentWithMessageBar(messageBarState = messageBarState) {
            AuthenticationComponent(
                loadingState = loadingState,
                onCLick = onButtonClicked
            )
        }
    }

    OneTapSignInWithGoogle(
        state = oneTapState,
        clientId = BuildConfig.CLIENT_ID,
        onTokenIdReceived = { token ->
            onTokenReceived.invoke(token)
        },
        onDialogDismissed = { message ->
            onDialogDismissed.invoke(Exception(message))
        })

    LaunchedEffect(key1 = authenticateState) {
        if(authenticateState){
            navigateToHomeScreen.invoke()
        }
    }
}