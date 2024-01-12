package com.example.advnative_waroengujang.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.advnative_waroengujang.model.User
import com.example.advnative_waroengujang.util.DatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {


    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val db = DatabaseUtil.buildDb(getApplication())

    fun login(username: String, password: String): LiveData<Boolean> {
        val isLoginValid = MutableLiveData<Boolean>()
        launch {
            val user= db.waroengDao().checkLogin(username)
            Log.e("LoginVM", "user: ${user?.username}")
            isLoginValid.postValue(user != null)
        }
        return isLoginValid
    }
}