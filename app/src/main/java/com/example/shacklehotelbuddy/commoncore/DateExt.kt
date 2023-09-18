package com.example.shacklehotelbuddy.commoncore

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale


fun todayInMillis() = Calendar.getInstance().timeInMillis

fun Date.plus(days: Int): Date {
    val cal = GregorianCalendar.getInstance().apply {
        time = this@plus
    }
    cal.add(DAY_OF_MONTH, days)
    return cal.time
}

fun Date.getDayOfMonth(): Int {
    val cal = GregorianCalendar.getInstance().apply {
        time = this@getDayOfMonth
    }
    return cal.get(DAY_OF_MONTH)
}

fun Date.getMonthOfYear(): Int {
    val cal = GregorianCalendar.getInstance().apply {
        time = this@getMonthOfYear
    }
    return cal.get(MONTH)
}

fun Date.getCurrentYear(): Int {
    val cal = GregorianCalendar.getInstance().apply {
        time = this@getCurrentYear
    }
    return cal.get(YEAR) + 1
}

fun formatDate(milliseconds: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    val date = Date(milliseconds)
    return dateFormat.format(date)
}






