package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.ChallengeDetailsDao
import com.codewarsclient.repositories.helpers.ApiResultWrapper
import com.codewarsclient.repositories.helpers.ChallengeDetailsWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeDetailsRepository @Inject constructor(
    private val dao: ChallengeDetailsDao,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    /**
     * Searches the details of a challenge
     *
     * # API communication success
     * - Stores the challenge details in the local database and returns them
     *
     * # API communication failure or Network error
     * - Attempts to obtain the challenge details from the local database
     */
    suspend fun getChallengeDetails(challengeId: String): ChallengeDetailsWrapper {
        val challengeDetailsApiResponse = safeApiCall(dispatcher) {
            apiService.getChallengeDetails(challengeId)
        }

        return when (challengeDetailsApiResponse) {
            is ApiResultWrapper.Success -> {
                val challengesDetails =
                    challengeDetailsApiResponse.value.toChallengeDetailsEntity()

                dao.insertChallengeDetails(challengesDetails)

                ChallengeDetailsWrapper(true, challengesDetails)
            }

            else -> ChallengeDetailsWrapper(false, dao.getChallengeDetails(challengeId))
        }
    }

}