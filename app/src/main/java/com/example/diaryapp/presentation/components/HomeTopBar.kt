package com.example.diaryapp.presentation.components

import android.widget.ImageButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onMenuItemClicked: () -> Unit
) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = onMenuItemClicked) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Hamburger menu icon")
        }
    },
        title = {
            Text(text = "Diary")
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "filter by date")
            }
        })
}