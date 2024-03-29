package com.example.advnative_waroengujang.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name="name")
    val name:String,

    @ColumnInfo(name="description")
    val description:String,

    @ColumnInfo(name="price")
    val price:Int,

    @ColumnInfo(name="photo_url")
    val photo:String,

    @ColumnInfo(name="category")
    val category:String
)
