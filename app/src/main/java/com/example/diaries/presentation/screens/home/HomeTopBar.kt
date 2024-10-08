package com.example.diaries.presentation.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onMenuClicked: () -> Unit) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = "Home")
        },
        navigationIcon = {
            IconButton(onClick = onMenuClicked){
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "Hamburger Menu Icon",
                    tint = MaterialTheme.colorScheme.onSurface)
            }
        },
        actions = {
            IconButton(onClick = onMenuClicked){
                Icon(imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Range Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}


