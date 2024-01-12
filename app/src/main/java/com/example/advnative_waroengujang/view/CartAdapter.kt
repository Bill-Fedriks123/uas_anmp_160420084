package com.example.advnative_waroengujang.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.model.Cart

class CartAdapter (var cartList:ArrayList<Cart>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var cartClickListener: CartClickListener? = null

    class CartViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val lblMenuNameCart = holder.view.findViewById<TextView>(R.id.lblMenuNameCart)
        val lblMenuPriceCart = holder.view.findViewById<TextView>(R.id.lblMenuPriceCart)
        val lblQuantityCart = holder.view.findViewById<TextView>(R.id.lblQuantityCart)
        val btnDecrease = holder.view.findViewById<Button>(R.id.btnDecreaseCart)
        val btnIncrease = holder.view.findViewById<Button>(R.id.btnIncreaseCart)

        lblMenuNameCart.text = cartList[position].menuName
        lblMenuPriceCart.text = "IDR ${cartList[position].menuPrice}"
        lblQuantityCart.text = cartList[position].quantity.toString()

        var quantity = Integer.parseInt(cartList[position].quantity.toString())
        btnDecrease.setOnClickListener {
            quantity--
            lblQuantityCart.text = quantity.toString()

            if(quantity == 0) {
                cartList.removeAt(position)
            } else {
                cartList[position].quantity = quantity
                CartFragment.listCart[position].quantity = quantity
            }
            cartClickListener?.onDataChanged(cartList)
        }

        btnIncrease.setOnClickListener {
            quantity++
            lblQuantityCart.text = quantity.toString()

            cartList[position].quantity = quantity
            CartFragment.listCart[position].quantity = quantity
            cartClickListener?.onDataChanged(cartList)
        }
    }
    interface CartClickListener {
        fun onDataChanged(newList: ArrayList<Cart>)
    }
}
