package com.example.diaries.data.repository

import com.example.diaries.model.Diary
import com.example.diaries.util.Constants.APP_ID
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration


object MongoDB : MongoRepo {
    private val app = App.create(APP_ID)

    private val user = app.currentUser
    private lateinit var realm: Realm

    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(user, setOf(Diary::class))
                .initialSubscriptions { sub ->
                    add(
                        query = sub.query(query = "ownerId == $0", user.id),
                        name = "User's Diaries"
                    )
                }
//                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }else {
            println("User is not logged in. Please log in to access your data.")
        }
    }
}
