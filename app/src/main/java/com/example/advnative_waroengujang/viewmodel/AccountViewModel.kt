package com.example.advnative_waroengujang.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderDetail
import com.example.advnative_waroengujang.model.User
import com.example.advnative_waroengujang.util.DatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val userAccount = MutableLiveData<User>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val db = DatabaseUtil.buildDb(getApplication())

    fun getUserById(userId: Int) {
        launch {
            val data = db.waroengDao().getUserData(userId)
            userAccount.postValue(data)
        }
    }

    fun updatePassword(oldPassword: String, newPassword: String): Boolean {
        val user = userAccount.value
        if (user != null) {
            return if (user.password == oldPassword) {
                launch {
                    val newUserData = User(
                        id = user.id,
                        name = user.name,
                        username = user.username,
                        password = newPassword,
                    )
                    db.waroengDao().updateUser(newUserData)
                }
                true
            } else {
                false
            }
        } else {
            return false
        }
    }
}