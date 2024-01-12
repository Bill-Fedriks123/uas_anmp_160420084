package com.example.advnative_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advnative_waroengujang.R
import com.example.advnative_waroengujang.view.HomeFragment.Companion.tableNumber
import com.example.advnative_waroengujang.viewmodel.ListMenuModel

class ListMenuFragment : Fragment() {
    private lateinit var viewModel: ListMenuModel
    private lateinit var listMenuAdapter: ListMenuAdapter

    companion object {
        var positionSelected = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lblChangeTableNumber = view.findViewById<TextView>(R.id.lblChangeTableNumberMenu)
        val lblTableNumber = view.findViewById<TextView>(R.id.lblTableNumberMenu)
        val listMenu = view.findViewById<RecyclerView>(R.id.listMenu)

        lblTableNumber.text = "Table Number " + tableNumber

        // Initialize View Model
        viewModel = ViewModelProvider(this)[ListMenuModel::class.java]
        viewModel.refresh()

        // Initialize Adapter and Recyclerview
        listMenuAdapter = ListMenuAdapter(arrayListOf(), tableNumber)
        listMenu.layoutManager = LinearLayoutManager(this.context)
        listMenu.adapter = listMenuAdapter

        observeViewModel()

        lblChangeTableNumber.setOnClickListener {
            val action = ListMenuFragmentDirections.actionListMenuFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val txtSearchMenu = view.findViewById<EditText>(R.id.txtSearchMenu)
    }

    private fun observeViewModel() {
        viewModel.menusLD.observe(viewLifecycleOwner){
            listMenuAdapter.updateListMenu(it)
        }
    }
}