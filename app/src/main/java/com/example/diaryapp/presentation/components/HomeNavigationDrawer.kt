package com.example.diaryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diaryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigationDrawer(
    drawerState: DrawerState,
    onSignOut: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "signOut logo"
                )
                NavigationDrawerItem(
                    label = {
                        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.google_logo),
                                contentDescription = "signOut logo"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = "Sign Out")
                        }
                    },
                    selected = false,
                    onClick = onSignOut
                )
            }
        },
        content = content
    )
}