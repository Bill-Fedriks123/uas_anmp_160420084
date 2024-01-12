package com.example.advnative_waroengujang.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.advnative_waroengujang.model.Menu
import com.example.advnative_waroengujang.model.Order
import com.example.advnative_waroengujang.model.OrderDetail
import com.example.advnative_waroengujang.model.User
import com.example.advnative_waroengujang.util.DatabaseUtil

@Database(entities = [Menu::class, Order::class, OrderDetail::class, User::class], version =  1)
abstract class WaroengDatabase: RoomDatabase() {
    abstract fun waroengDao(): WaroengDao

    companion object {
        @Volatile private var instance: WaroengDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WaroengDatabase::class.java,
                "waroengdb")
                .addMigrations(DatabaseUtil.MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}
