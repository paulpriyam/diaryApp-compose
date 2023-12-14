package com.example.diaryapp.presentation.screens.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.diaryapp.BuildConfig
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    loadingState: Boolean = false,
    oneTapState:OneTapSignInState,
    onButtonClicked: () -> Unit = {}
) {
    Scaffold {
        AuthenticationComponent(
            loadingState = loadingState,
            onCLick = onButtonClicked
        )
    }

    OneTapSignInWithGoogle(
        state =oneTapState ,
        clientId = BuildConfig.CLIENT_ID,
        onTokenIdReceived ={token->
            Log.d("TAG", "AuthenticationScreen: token received $token")
        } ,
        onDialogDismissed = {message->
            Log.d("TAG", "AuthenticationScreen: dialogDismissed $message")
        })
}