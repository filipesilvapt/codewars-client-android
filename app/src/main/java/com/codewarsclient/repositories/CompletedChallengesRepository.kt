package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.CompletedChallengeDao
import com.codewarsclient.database.entities.CompletedChallengesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompletedChallengesRepository @Inject constructor(
    private val dao: CompletedChallengeDao,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    suspend fun getMemberCompletedChallenges(
        username: String,
        pageNumber: Int
    ): List<CompletedChallengesEntity>? {
        val challengesApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberCompletedChallenges(username, pageNumber)
        }

        return when (challengesApiResponse) {
            is RepositoryResultWrapper.NetworkError -> null
            is RepositoryResultWrapper.Failure -> null
            is RepositoryResultWrapper.Success -> {
                val challengesEntityList =
                    challengesApiResponse.value.toCompletedChallengeEntityList(username)

                dao.insertChallengesList(challengesEntityList)

                challengesEntityList
            }
        }
    }

}