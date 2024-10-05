package com.example.diaries.presentation.screens.write

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WriteScreen(onBackClicked: () -> Unit) {
    Scaffold(
        topBar = {
            WriteTopBar(onBackClicked = onBackClicked)
        },
        content = {

        }
    )
}