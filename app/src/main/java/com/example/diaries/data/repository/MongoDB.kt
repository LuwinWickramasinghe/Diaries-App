package com.example.diaries.data.repository

import android.util.Log
import com.example.diaries.model.Diary
import com.example.diaries.util.Constants.APP_ID
import com.example.diaries.util.RequestState
import com.example.diaries.util.toInstant
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId


object MongoDB : MongoRepo {
    private val app = App.create(APP_ID)

    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }

    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(user, setOf(Diary::class))
                .initialSubscriptions { sub ->
                    add(
                        query = sub.query<Diary>(query = "ownerId == $0", user.id),
                        name = "User's Diaries"
                    )
                }
//                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
            realm.subscriptions.refresh()

        }
    }

    override fun getAllDiaries(): Flow<Diaries> {
        return if(user != null){
            try {
                Log.d("exug", "User id ${user.id}")
                realm.query<Diary>(query = "ownerId == $0", user.id)
                    .sort(property = "date", sortOrder = Sort.DESCENDING)
                    .asFlow()
                    .map { result ->
                        Log.d("exug", "Query Result: ${result.list}")
                        RequestState.Success(
                            data = result.list.groupBy {
                                it.date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            }
                        )
                    }
            }catch (e: Exception){
                flow { emit(RequestState.Error(e)) }
            }
        }else{
            flow { emit(RequestState.Error(NullUserException())) }
        }
    }
}

private class NullUserException : Exception("User is not logged in")