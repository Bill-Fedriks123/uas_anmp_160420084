package com.example.advnative_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.model.Cart
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderDetail
import com.example.advnative_waroengujang.view.HomeFragment.Companion.tableNumber
import com.example.advnative_waroengujang.viewmodel.ListMenuModel
import com.squareup.picasso.Picasso

class DetailMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lblMenuNameDetail = view.findViewById<TextView>(R.id.lblMenuNameDetail)
        val lblDescriptionDetail = view.findViewById<TextView>(R.id.lblDescriptionDetail)
        val lblMenuPriceDetail = view.findViewById<TextView>(R.id.lblMenuPriceDetail)
        val lblQuantityDetail = view.findViewById<TextView>(R.id.lblQuantityDetail)

        val btnDecrease = view.findViewById<Button>(R.id.btnDecrease)
        val btnIncrease = view.findViewById<Button>(R.id.btnIncrease)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val imgMenuDetail = view.findViewById<ImageView>(R.id.imgMenuDetail)

        var quantity = 1
        lblQuantityDetail.text = quantity.toString()

        btnDecrease.setOnClickListener {
            if(quantity > 1) {
                quantity--
                lblQuantityDetail.text = quantity.toString()
            }
        }

        btnIncrease.setOnClickListener {
            quantity++
            lblQuantityDetail.text = quantity.toString()
        }

        // get data from args
        val args = DetailMenuFragmentArgs.fromBundle(requireArguments())
        lblMenuNameDetail.text = args.name
        lblDescriptionDetail.text = args.description
        lblMenuPriceDetail.text = "IDR " + args.price

        Picasso.get().load(args.photo).into(imgMenuDetail)

        btnAdd.setOnClickListener {
            val cart = Cart(
                args.menuId,
                args.name,
                args.price,
                args.photo,
                quantity
            )

            CartFragment.listCart.add(cart)

            lblQuantityDetail.text = quantity.toString()

            val action = DetailMenuFragmentDirections.actionDetailMenuFragmentToListMenuFragment()
            Navigation.findNavController(it).navigate(action)

            Toast.makeText(this.context, "Menu added to cart!", Toast.LENGTH_LONG).show()
        }
    }
}