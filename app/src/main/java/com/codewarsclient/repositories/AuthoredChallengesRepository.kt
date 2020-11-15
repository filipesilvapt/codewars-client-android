package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.api.models.AuthoredChallengesModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthoredChallengesRepository @Inject constructor(
    //private val dao: AuthoredChallengesDao,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    suspend fun getMemberAuthoredChallenges(username: String): AuthoredChallengesModel? {
        val challengesApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberAuthoredChallenges(username)
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