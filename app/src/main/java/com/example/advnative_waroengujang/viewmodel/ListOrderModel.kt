package com.example.advnative_waroengujang.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advnative_waroengujang.model.OrderWithDetail
import com.example.advnative_waroengujang.util.DatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListOrderModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val ordersLD = MutableLiveData<List<OrderWithDetail>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val db = DatabaseUtil.buildDb(getApplication())

    fun refresh() {
        launch {
            val data = db.waroengDao().getAllOrder()
            ordersLD.postValue(data)
        }
    }
}