package com.example.diaries.data.repository

import com.example.diaries.model.Diary
import com.example.diaries.util.RequestState
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

typealias Diaries = RequestState<Map<LocalDate, List<Diary>>>

interface MongoRepo {
    fun configureTheRealm()
    fun getAllDiaries(): Flow<Diaries>
}