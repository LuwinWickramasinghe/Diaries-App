package com.example.diaries.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diaries.R
import com.example.diaries.data.repository.Diaries
import com.example.diaries.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    diaries: Diaries,
    drawerState: DrawerState,
    onMenuClicked: () -> Unit,
    onSignOutClick: () -> Unit,
    navigateToWrite: () -> Unit
) {
    var padding by remember {
        mutableStateOf(PaddingValues())
    }
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    NavigationDrawer(
        drawerState = drawerState,
        onSignOutClick = onSignOutClick
    ) {
        Scaffold (
            modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                HomeTopBar (
                    scrollBehavior = scrollBehaviour,
                    onMenuClicked = onMenuClicked
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(top = padding.calculateEndPadding(androidx.compose.ui.unit.LayoutDirection.Ltr)),
                    onClick = navigateToWrite
                ){
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "New diary"
                    )
                }
            },
            content = {
                padding = it
                when(diaries){
                    is RequestState.Success -> {
                        Log.d("exbug", diaries.data.toString())
                        HomeContent(
                            paddingValues = it,
                            diaryNotes = diaries.data,
                            onClick = {}
                        )
                    }
                    is RequestState.Error -> {
                        EmptyPage(
                            title = "Error",
                            subtitle = "${diaries.error.message}"
                        )
                    }
                    is RequestState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }

                    }
                    else -> {}
                }

            }

        )
    }
}

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    onSignOutClick : () ->Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Image(
                            modifier = Modifier.size(250.dp),
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo Image"
                        )
                    }
                    NavigationDrawerItem(
                        label = {
                            Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.google_logo),
                                    contentDescription = "Google logo for sign out"
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "Sign Out", color = MaterialTheme.colorScheme.onSurface)
                            }
                        },
                        selected = false,
                        onClick = onSignOutClick
                    )
                }
            )
        },
        content = content
    )
}