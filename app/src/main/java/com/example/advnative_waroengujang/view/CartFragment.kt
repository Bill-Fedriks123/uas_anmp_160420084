package com.example.advnative_waroengujang.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.model.Cart
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderDetail
import com.example.advnative_waroengujang.viewmodel.CartViewModel

class CartFragment : Fragment(), CartAdapter.CartClickListener {
    companion object {
        var listCart = ArrayList<Cart>()
    }

    private lateinit var viewCf: View
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lblTableNumber = view.findViewById<TextView>(R.id.lblTableNumberCart)
        lblTableNumber.text = "Table Number " + HomeFragment.tableNumber

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

        var cartAdapter = CartAdapter(listCart)
        cartAdapter.cartClickListener = this

        var listCart = view.findViewById<RecyclerView>(R.id.listCart)
        listCart.layoutManager = LinearLayoutManager(this.context)
        listCart.adapter = cartAdapter

        viewCf = view
        calculate()

        val btnProcess = view.findViewById<Button>(R.id.btnProcess)
        btnProcess.setOnClickListener {
            processToKitchen()
        }
    }

    override fun onResume() {
        super.onResume()
        calculate()
    }

    override fun onStart() {
        super.onStart()
        calculate()
    }

    override fun onDataChanged(newList: ArrayList<Cart>) {
        Log.e("CartFragment", "onDatachanged")
        listCart = newList
        calculate()
    }

    private fun processToKitchen(){
        if (listCart.isEmpty()){
            Toast.makeText(this.context, "Cart is Empty!", Toast.LENGTH_LONG).show()
        } else {
            var subtotal = 0

            val orderDate = System.currentTimeMillis()

            // Unique Order ID Format: ORDER + orderDate
            val orderId = "ORDER$orderDate"

            val orderDetailList = ArrayList<OrderDetail>()

            for(cart in listCart) {
                subtotal += cart.menuPrice * cart.quantity
                val orderDetail = OrderDetail(
                    menuName = cart.menuName,
                    menuPrice = cart.menuPrice,
                    quantity = cart.quantity,
                    menuId = cart.menuId,
                    orderParentId = orderId // Foreign Key of Order
                )
                orderDetailList.add(orderDetail)
            }

            var tax = subtotal * 10 / 100
            var total = subtotal + tax

            val order = Order(
                tableNumber = HomeFragment.tableNumber,
                total = total,
                orderDate = System.currentTimeMillis(),
                orderId = orderId
            )

            cartViewModel.createOrder(order, orderDetailList)
            Toast.makeText(this.context, "Cart has been sent to kitchen!", Toast.LENGTH_LONG).show()

            // clear cart
            listCart.clear()

            // update view
            calculate()
        }
    }

    fun calculate() {
        val lblSubtotal = viewCf.findViewById<TextView>(R.id.lblSubtotal)
        val lblTax = viewCf.findViewById<TextView>(R.id.lblTax)
        val lblTotal = viewCf.findViewById<TextView>(R.id.lblTotal)

        var subtotal = 0
        for(cart in listCart) {
            subtotal += cart.menuPrice * cart.quantity
        }

        var tax = subtotal * 10 / 100
        var total = subtotal + tax

        lblSubtotal.text = "IDR $subtotal"
        lblTax.text = "IDR $tax"
        lblTotal.text = "IDR $total"
    }
}