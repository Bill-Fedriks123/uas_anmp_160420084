package com.example.advnative_waroengujang.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("setPriceNumber")
fun setPriceNumber(view: TextView, value: Int?) {
    view.text = "IDR $value"
}

@InverseBindingAdapter(attribute = "my_number")
fun getNumber(view: TextView): Int {
    return view.text.toString().toIntOrNull() ?: 0
}