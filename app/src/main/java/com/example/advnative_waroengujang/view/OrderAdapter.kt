package com.example.advnative_waroengujang.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderWithDetail
import com.example.advnative_waroengujang.util.DateHelper

class OrderAdapter() : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private var orderList = ArrayList<OrderWithDetail>()

    class OrderViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_item, parent, false)

        return OrderViewHolder(view)

    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val lblTableNumberOrder = holder.view.findViewById<TextView>(R.id.lblTableNumberOrder)
        val lblTotalPrice = holder.view.findViewById<TextView>(R.id.lblTotalPrice)
        val lblDuration = holder.view.findViewById<TextView>(R.id.lblDuration)

        val order = orderList[position].order

        lblTableNumberOrder.text = order.tableNumber.toString()
        lblTotalPrice.text = "IDR ${order.total}"
        val formattedTime = DateHelper.getTimeFromTimestamp(order.orderDate)
        lblDuration.text = "Duration: $formattedTime"
    }

    fun updateListOrder(newOrderList: List<OrderWithDetail>) {
        orderList.clear()
        orderList.addAll(newOrderList)
        notifyDataSetChanged()
    }

}