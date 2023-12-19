package com.example.palace_resorts.utils.date

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    fun formatDate(date: String): String {
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-DD")
        val date: Date = formatter.parse(date) as Date
        val newFormat = SimpleDateFormat(DATE_FORMAT)
        return  newFormat.format(date)
    }

    private const val DATE_FORMAT = "dd MMMM yyyy"
}