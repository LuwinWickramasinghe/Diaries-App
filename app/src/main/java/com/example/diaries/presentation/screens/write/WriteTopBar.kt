package com.example.diaries.presentation.screens.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.diaries.model.Diary
import com.example.diaries.presentation.components.DisplayAlertDialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTopBar(
    selectedDiary: Diary?,
    onBackClicked: () -> Unit,
    onDeleteYes: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Happy",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Date dd mm yyyy",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    ),
                    textAlign = TextAlign.Center

                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date icon"
                )
            }
            if (selectedDiary != null) {
                DeleteDiary(
                    selectedDiary,
                    onDeleteYes = onDeleteYes
                )
            }
        }
    )

}

@Composable
fun DeleteDiary(
    selectedDiary: Diary?,
    onDeleteYes: () -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {expanded = false}
    ) {
        DropdownMenuItem(
            text = {
                Text(text = "Delete")
            }, onClick = {
                openDialog = true
                expanded = true
            }
        )
    }
    DisplayAlertDialog(
        title = "Delete",
        message = "Are you sure you want to delete this diary permanantly?'$(selectedDiary?.title)' ",
        dialogOpened = openDialog,
        onDialogClosed = {openDialog = false},
        onYesClicked = onDeleteYes
    )
    IconButton(
        onClick = {expanded = !expanded}
    )

         {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Oberflow menu icon",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

}