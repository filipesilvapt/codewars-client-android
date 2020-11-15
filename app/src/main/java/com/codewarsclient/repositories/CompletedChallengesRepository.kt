package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.api.models.CompletedChallengesModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompletedChallengesRepository @Inject constructor(
    //private val dao: CompletedChallengesDao,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    suspend fun getMemberCompletedChallenges(
        username: String,
        pageNumber: Int
    ): CompletedChallengesModel? {
        val challengesApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberCompletedChallenges(username, pageNumber)
        }

        return when (challengesApiResponse) {
            is RepositoryResultWrapper.NetworkError -> null
            is RepositoryResultWrapper.Failure -> null
            is RepositoryResultWrapper.Success -> {
                challengesApiResponse.value
            }
        }
    }

}