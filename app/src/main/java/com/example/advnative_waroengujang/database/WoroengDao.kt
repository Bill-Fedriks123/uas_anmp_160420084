package com.example.advnative_waroengujang.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.advnative_waroengujang.model.Menu
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderDetail
import com.example.advnative_waroengujang.model.OrderWithDetail
import com.example.advnative_waroengujang.model.User

@Dao
interface WaroengDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMenu(menuList: List<Menu>)

    @Query("SELECT * FROM menu")
    fun selectAllMenu(): List<Menu>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Order)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderDetailList(orderDetails: List<OrderDetail>)

    @Transaction
    @Query("SELECT * FROM `order`")
    fun getAllOrder(): List<OrderWithDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(users: List<User>)

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUserData(userId: Int): User

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM User WHERE username = :username")
    fun checkLogin(username: String): User
}