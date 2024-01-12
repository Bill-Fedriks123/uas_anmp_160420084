package com.example.advnative_waroengujang

import android.app.Application
import androidx.room.Database
import com.example.advnative_waroengujang.model.User
import com.example.advnative_waroengujang.util.DatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BaseApplication: Application(), CoroutineScope{
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onCreate() {
        super.onCreate()

        val db = DatabaseUtil.buildDb(this)

        // pre-populate Database for the first launch
        val menuList = DatabaseUtil.menuList

        val user1 = User(
            id = 1,
            name = "Messi",
            username = "lm10",
            password = "123"
        )
        val user2 = User(
            id = 2,
            name = "Ronaldo",
            username = "cr7",
            password = "123"
        )

        launch(Dispatchers.IO) {
            db.waroengDao().insertAllMenu(menuList)
            db.waroengDao().insertAllUser(listOf(user1, user2))
        }
    }
}