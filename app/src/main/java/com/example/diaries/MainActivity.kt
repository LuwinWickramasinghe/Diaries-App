package com.example.diaries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.diaries.navigation.Screen
import com.example.diaries.navigation.SetupNavGraph
import com.example.diaries.ui.theme.DiariesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            DiariesTheme {
                val navController = rememberNavController()
                    SetupNavGraph(
                        startDestination = Screen.Authentication.route,
                        navController = navController )

                }
            }
        }
    }


