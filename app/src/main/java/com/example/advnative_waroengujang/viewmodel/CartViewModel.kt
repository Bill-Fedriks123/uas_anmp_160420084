package com.example.advnative_waroengujang.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderDetail
import com.example.advnative_waroengujang.util.DatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CartViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {


    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val db = DatabaseUtil.buildDb(getApplication())

    fun createOrder(order: Order, orderDetailList: List<OrderDetail>){
        launch {
            db.waroengDao().insertOrder(order)
            db.waroengDao().insertOrderDetailList(orderDetailList)
        }
    }
}