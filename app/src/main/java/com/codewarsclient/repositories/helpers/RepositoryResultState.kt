package com.codewarsclient.repositories.helpers

enum class RepositoryResultState {
    SUCCESS_API,
    SUCCESS_DB_AFTER_API,
    SUCCESS_DB_OFFLINE,
    NOT_FOUND_API,
    NOT_FOUND_DB_OFFLINE,
    ERROR_API
}