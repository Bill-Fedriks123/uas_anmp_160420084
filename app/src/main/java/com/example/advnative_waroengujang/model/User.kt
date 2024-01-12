package com.example.advnative_waroengujang.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="username")
    val username: String,

    @ColumnInfo(name="password")
    val password: String,
)