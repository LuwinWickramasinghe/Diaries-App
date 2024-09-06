package com.example.diaries.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaries.data.repository.Diaries
import com.example.diaries.data.repository.MongoDB
import com.example.diaries.util.RequestState
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var diaries: MutableState<Diaries> = mutableStateOf(RequestState.Idle)

    init {
        observeDiaries()

    }

    private fun observeDiaries(){
        viewModelScope.launch {
            MongoDB.getAllDiaries().collect{
                    result -> diaries.value = result

            }
        }
    }
}