package com.example.diaryapp.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.R
import com.example.diaryapp.presentation.components.GoogleButton

@Preview
@Composable
fun AuthenticationComponent(
    loadingState:Boolean=false,
    onCLick:()->Unit={}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier=Modifier.weight(10f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google logo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Welcome back", fontSize = MaterialTheme.typography.titleLarge.fontSize)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please Sign in to Continue",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
        Column(modifier=Modifier.weight(2f), verticalArrangement = Arrangement.Center) {
            GoogleButton(
                loadingState = loadingState,
                onClick = onCLick
            )
        }
    }
}