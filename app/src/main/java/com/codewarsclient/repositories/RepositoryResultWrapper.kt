package com.codewarsclient.repositories

sealed class RepositoryResultWrapper<out T> {
    data class Success<out T>(val value: T) : RepositoryResultWrapper<T>()
    data class Failure(val code: Int? = null) : RepositoryResultWrapper<Nothing>()
    object NetworkError : RepositoryResultWrapper<Nothing>()
}