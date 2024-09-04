package com.example.diaries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.diaries.navigation.Screen
import com.example.diaries.navigation.SetupNavGraph
import com.example.diaries.ui.theme.DiariesTheme
import com.example.diaries.util.Constants.APP_ID
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            DiariesTheme {
                val navController = rememberNavController()
                    SetupNavGraph(
                        startDestination = getStartDestination(),
                        navController = navController )

                }
            }
        }
    }

private fun getStartDestination(): String {
    val user = App.create(APP_ID).currentUser
    return if(user!= null && user.loggedIn) Screen.Home.route
    else Screen.Authentication.route
}


