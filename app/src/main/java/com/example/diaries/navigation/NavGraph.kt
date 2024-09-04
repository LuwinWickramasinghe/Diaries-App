package com.example.diaries.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.diaries.presentation.components.DisplayAlertDialog
import com.example.diaries.presentation.screens.auth.AuthenticationScreen
import com.example.diaries.presentation.screens.auth.AuthenticationViewModel
import com.example.diaries.presentation.screens.home.HomeScreen
import com.example.diaries.util.Constants.APP_ID
import com.example.diaries.util.Constants.WRITE_SCREEN_ARGUMENT_KEY

import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(startDestination = startDestination,
        navController = navController)
    {
        authenticationRoute(
            navigateToHome = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        )
        homeRoute(navigateToWrite = {
            navController.navigate(Screen.Write.route)
        },
            navigateToAuth = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.route)
            })
        writeRoute()
    }
}

fun NavGraphBuilder.authenticationRoute(
    navigateToHome:() -> Unit
){
    composable(route = Screen.Authentication.route){
        val viewModel: AuthenticationViewModel = viewModel()
        val loadingState by viewModel.loadingState
        val authenticated by viewModel.authenticated
        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()

        AuthenticationScreen(
            authenticated= authenticated,
            loadingState = loadingState,
            oneTapState= oneTapState,
            messageBarState = messageBarState,
            onButtonClicked = {
                oneTapState.open()
                viewModel.setLoading(true)

            },
            onTokenIdReceived ={ tokenId ->
                viewModel.signInWithMongoAtlas(
                    tokenId = tokenId,
                    onSuccess = {
                        messageBarState.addSuccess("Successfully Signed in!")
                        viewModel.setLoading(false)
                    },
                    onError = {
                        messageBarState.addError(Exception(it))
                        viewModel.setLoading(false)
                    }
                )

            },
            onDialogDismissed = {message ->
                messageBarState.addError(Exception(message))
                viewModel.setLoading(false)
            },
            navigateToHome = {
                navigateToHome()
            }
        )
    }
}

fun NavGraphBuilder.homeRoute(
    navigateToWrite: () -> Unit,
    navigateToAuth: () -> Unit
){
    composable(route = Screen.Home.route){
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        var signOutDialogOpen by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()

        HomeScreen(
            drawerState = drawerState,
            onMenuClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            onSignOutClick = {
                signOutDialogOpen = true
            },
            navigateToWrite = navigateToWrite
        )

        DisplayAlertDialog(
            title = "SignOut",
            message = "Are you sure?",
            dialogOpened = signOutDialogOpen,
            onDialogClosed = { signOutDialogOpen = false },
            onYesClicked = {
                scope.launch (Dispatchers.IO){
                    val user = App.create(APP_ID).currentUser
                    if (user != null) {
                        user.logOut()
                        withContext(Dispatchers.Main){
                            navigateToAuth()
                        }

                    }
                }
            }
        )
    }
}

fun NavGraphBuilder.writeRoute(){
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name= WRITE_SCREEN_ARGUMENT_KEY){
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ){

    }
}