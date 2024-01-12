package com.example.advnative_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.viewmodel.ListMenuModel
import com.example.advnative_waroengujang.viewmodel.ListOrderModel

class OrderFragment : Fragment() {
    private lateinit var viewModel: ListOrderModel
    private val orderAdapter = OrderAdapter()

    companion object {
        var listOrder = ArrayList<Order>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListOrderModel::class.java)
        viewModel.refresh()

        val listOrder = view.findViewById<RecyclerView>(R.id.listOrder)
        listOrder.layoutManager = LinearLayoutManager(this.context)
        listOrder.adapter = orderAdapter
        observeViewModel()

//        val popUp = view.findViewById<RecyclerView>(R.id.btn_showPopUp)
//
//        button.setOnClickListener {
//            val showPopUp = OrderPopUpFragment()
//            showPopUp.show((activity as AppCompatActivity).supportFragmentManager. "showPopUp")
//        }

    }

    private fun observeViewModel() {
        viewModel.ordersLD.observe(viewLifecycleOwner){
            orderAdapter.updateListOrder(it)
        }
    }
}