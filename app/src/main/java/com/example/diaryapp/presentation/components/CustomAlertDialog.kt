package com.example.diaryapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    showAlertDialog: MutableState<Boolean>,
    onDialogClose: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (showAlertDialog.value) {
        AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(CornerSize(size = 8.dp))),
            onDismissRequest = {
                onDialogClose.invoke()
            })
        {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = onDialogClose) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = onYesClicked) {
                        Text(text = "Yes")
                    }
                }
            }

        }
    }
}