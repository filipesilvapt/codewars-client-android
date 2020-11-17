package com.codewarsclient.utils

import java.util.*

object Utils {

    /**
     * Normalizes a languages list by capitalizing each one and joining them into a final string
     * separated by commas
     */
    fun capitalizeAndJoinLanguagesList(languagesList: List<String>): String {
        return languagesList.map {
            it.capitalize(Locale.getDefault())
        }.joinToString { it }
    }

}