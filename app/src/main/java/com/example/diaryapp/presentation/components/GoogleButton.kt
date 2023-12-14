package com.example.diaryapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.diaryapp.R

@Preview
@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = true,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "please wait...",
    shape: Shape = Shapes().extraSmall,
    icon: Int = R.drawable.google_logo,
    borderStrokeWidth: Dp = 1.dp,
    borderColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {
    var buttonText by remember {
        mutableStateOf(primaryText)
    }

    LaunchedEffect(key1 = loadingState) {
        buttonText = if(loadingState) secondaryText else primaryText
    }

    Surface(
        modifier = modifier.clickable(enabled = !loadingState) { onClick.invoke() },
        shape = shape,
        border = BorderStroke(width = borderStrokeWidth, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = "google icon", tint = Color.Unspecified)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buttonText, fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            if (loadingState) {
                Spacer(modifier = Modifier.width(8.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = progressIndicatorColor,
                    strokeWidth = 2.dp
                )
            }
        }
    }
}