package com.example.advnative_waroengujang.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Order (
    @PrimaryKey()
    val orderId:String = "",

    @ColumnInfo(name="table_number")
    val tableNumber:Int,

    @ColumnInfo(name="total")
    val total: Int = 0,

    @ColumnInfo(name="order_date")
    val orderDate: Long = 0L, // Using Timestamp
)


@Entity
data class OrderDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,

    val orderParentId: String,

    @ColumnInfo(name="menuId")
    val menuId: Int,

    @ColumnInfo(name="menuName")
    val menuName: String,

    @ColumnInfo(name="menuPrice")
    val menuPrice: Int,

    @ColumnInfo(name="quantity")
    val quantity: Int,
)

data class OrderWithDetail(
    @Embedded val order: Order,

    // Table Relation one To Many between Order table and OrderDetail table
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderParentId",
    )
    val orderDetailList: List<OrderDetail>
)