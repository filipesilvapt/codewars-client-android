package com.codewarsclient.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val FORMAT_DATE_TIME_API = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val FORMAT_DATE_TIME_DISPLAY = "yyyy-MM-dd HH:mm:ss"

    /**
     * Converts a date from the api format to the format that we want to display
     */
    fun convertApiDateTimeToDisplay(dateToConvert: String): String {
        val apiFormat: DateFormat = SimpleDateFormat(FORMAT_DATE_TIME_API, Locale.getDefault())
        val displayFormat: DateFormat =
            SimpleDateFormat(FORMAT_DATE_TIME_DISPLAY, Locale.getDefault())
        val date = apiFormat.parse(dateToConvert)
        return displayFormat.format(date ?: "")
    }

    /**
     * Returns the current date and time in the api format
     */
    fun getCurrentApiDateTime(): String {
        val apiFormat: DateFormat = SimpleDateFormat(FORMAT_DATE_TIME_API, Locale.getDefault())
        return apiFormat.format(Date())
    }

    /**
     * Returns true if the first date time is older than the second date time
     */
    fun isOneApiDateTimeOlderThanTheOther(firstDateTime: String, secondDateTime: String): Boolean {
        val apiFormat: DateFormat = SimpleDateFormat(FORMAT_DATE_TIME_API, Locale.getDefault())
        val firstDate = apiFormat.parse(firstDateTime)
        val secondDate = apiFormat.parse(secondDateTime)
        return secondDate?.let {
            firstDate?.before(it) ?: false
        } ?: false
    }
}