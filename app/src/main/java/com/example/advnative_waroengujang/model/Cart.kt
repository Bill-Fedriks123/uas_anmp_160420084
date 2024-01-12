package com.example.advnative_waroengujang.model

data class Cart(
    val menuId: Int,
    val menuName:String,
    val menuPrice:Int,
    val menuPhoto:String,
    var quantity:Int
)
