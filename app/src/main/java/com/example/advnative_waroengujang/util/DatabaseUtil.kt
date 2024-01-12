package com.example.advnative_waroengujang.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.advnative_waroengujang.database.WaroengDatabase
import com.example.advnative_waroengujang.model.Menu

object DatabaseUtil {
    val DB_NAME = "waroengdb"
    fun buildDb(context: Context): WaroengDatabase {
        val db = Room.databaseBuilder(context, WaroengDatabase::class.java, DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .build()
        return db
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE menu ADD COLUMN finish INTEGER DEFAULT 1 not null"
            )
        }
    }

    val menuList = listOf(
        Menu(
            id = 1,
            name = "Ayam Saus Inggris",
            description = "Lorem ipsum dolor sit amet",
            price = 25000,
            photo = "https://2.bp.blogspot.com/-CJx7QU8WEjM/WBGpdDkt3EI/AAAAAAAAAxA/lNGFehyV3-II_DCW3ZeBvmXIutazfZNMgCLcB/s1600/ayam%2Bbakar2.png",
            category = "Main Course"
        ),
        Menu(
            id = 2,
            name = "Ayam Kalasan",
            description = "Lorem ipsum dolor sit amet",
            price = 25000,
            photo = "https://www.dapurkobe.co.id/wp-content/uploads/ayam-kalasan-kremes.jpg",
            category = "Main Course"
        ),
        Menu(
            id = 3,
            name = "Ayam Kremes",
            description = "Lorem ipsum dolor sit amet",
            price = 25000,
            photo = "https://www.jossdelapanbelas.com/wp-content/uploads/2022/02/Nasi-Bakar-Ayam-Kremes_prod-1024x1024.png",
            category = "Main Course"
        ),
        Menu(
            id = 4,
            name = "Es Teh",
            description = "Lorem ipsum dolor sit amet",
            price = 4000,
            photo = "https://order.ayambakarpakde.com/wp-content/uploads/2021/12/ayam-bakar-pak-d-ala-carte-es-teh.png",
            category = "Drink"
        ),
        Menu(
            id = 5,
            name = "Es Jeruk",
            description = "Lorem ipsum dolor sit amet",
            price = 6000,
            photo = "https://order.ayambakarpakde.com/wp-content/uploads/2021/12/ayam-bakar-pak-d-es-jeruk.png",
            category = "Drink"
        )
    )
}
