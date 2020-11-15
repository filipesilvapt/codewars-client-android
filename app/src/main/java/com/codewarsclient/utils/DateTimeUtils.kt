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
    fun convertDateTimeToDisplay(dateToConvert: String): String {
        val apiFormat: DateFormat = SimpleDateFormat(FORMAT_DATE_TIME_API, Locale.getDefault())
        val displayFormat: DateFormat =
            SimpleDateFormat(FORMAT_DATE_TIME_DISPLAY, Locale.getDefault())
        val date = apiFormat.parse(dateToConvert)
        return displayFormat.format(date ?: "")
    }
}