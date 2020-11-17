package com.codewarsclient.utils


object FileAccessUtil {

    fun readFileIntoString(filename: String): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(filename)
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }
}