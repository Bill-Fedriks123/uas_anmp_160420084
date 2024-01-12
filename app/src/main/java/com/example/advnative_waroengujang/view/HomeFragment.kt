package com.example.advnative_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advnative_waroengujang.R

class HomeFragment : Fragment() {
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        var tableNumber = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var name = activity?.getIntent()?.extras?.getString("name")

        val lblName = view.findViewById<TextView>(R.id.lblName)
        val lblServing = view.findViewById<TextView>(R.id.lblServing)
        val lblTableNumber = view.findViewById<TextView>(R.id.lblTableNumber)
        val lblChange = view.findViewById<TextView>(R.id.lblChange)
        val lblServe = view.findViewById<TextView>(R.id.lblServe)
        val txtTableNumber = view.findViewById<EditText>(R.id.txtTableNumber)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)

        lblName.text = name
        lblServing.visibility = View.GONE
        lblTableNumber.visibility = View.GONE
        lblChange.visibility = View.GONE

        if(txtTableNumber.text.toString() != "") {
            tableNumber = Integer.parseInt(txtTableNumber.text.toString())
        }

        btnSubmit.setOnClickListener {
            if(txtTableNumber.text.toString() != "") {
                tableNumber = Integer.parseInt(txtTableNumber.text.toString())
                if (tableNumber > 0 && tableNumber <= 20) {
                    lblServing.visibility = View.VISIBLE
                    lblTableNumber.visibility = View.VISIBLE
                    lblChange.visibility = View.VISIBLE
                    lblServe.visibility = View.GONE
                    txtTableNumber.visibility = View.GONE
                    btnSubmit.visibility = View.GONE

                    lblTableNumber.text = "Table " + Integer.toString(tableNumber)
                } else {
                    Toast.makeText(
                        this.context,
                        "Please input number range 1 - 20!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } else {
                Toast.makeText(
                    this.context,
                    "Please input number range 1 - 20!",
                    Toast.LENGTH_LONG
                )
            }
        }

        lblChange.setOnClickListener {
            lblServing.visibility = View.GONE
            lblTableNumber.visibility = View.GONE
            lblChange.visibility = View.GONE
            lblServe.visibility = View.VISIBLE
            txtTableNumber.visibility = View.VISIBLE
            btnSubmit.visibility = View.VISIBLE

            if (tableNumber != 0){
                txtTableNumber.setText(tableNumber.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val txtTableNumber = view?.findViewById<EditText>(R.id.txtTableNumber)
        if (tableNumber != 0){
            txtTableNumber?.setText(tableNumber.toString())
        }
    }
}