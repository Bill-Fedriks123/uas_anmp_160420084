package com.example.advnative_waroengujang.database

import android.content.Context
import android.content.SharedPreferences

class LocalPreference(
    private val context: Context
) {
    private var mSharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", 0)

    fun startSession(userId: Int) {
        val editor = mSharedPreferences?.edit()
        editor?.putInt("USER_ID", userId)
        editor?.putBoolean("IS_LOGGED_IN", true)
        editor?.apply()
    }

    fun clearSession() {
        val editor = mSharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }

    fun isLoggedIn(): Boolean? {
        return mSharedPreferences?.getBoolean("IS_LOGGED_IN", false)
    }

    fun getUserId(): Int? {
        return mSharedPreferences?.getInt("USER_ID", 1)
    }
}