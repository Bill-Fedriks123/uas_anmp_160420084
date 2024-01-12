package com.example.advnative_waroengujang.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {
    private const val TIME_MESSAGE_FORMAT = "hh:mm a" // 08:20 PM

    /**
     * @param Timestamp
     * @return time, example: 08:00 PM
     */
    fun getTimeFromTimestamp(timestamp: Long): String {
        val timestampObj = Timestamp(timestamp)
        val dateFormat = SimpleDateFormat(TIME_MESSAGE_FORMAT, Locale.getDefault())
        val date = Date(timestampObj.time)
        return dateFormat.format(date)
    }
}