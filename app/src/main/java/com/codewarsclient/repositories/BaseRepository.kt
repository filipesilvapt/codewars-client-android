package com.codewarsclient.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): RepositoryResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                RepositoryResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> RepositoryResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        RepositoryResultWrapper.Failure(code)
                    }
                    else -> {
                        RepositoryResultWrapper.Failure(null)
                    }
                }
            }
        }
    }

}