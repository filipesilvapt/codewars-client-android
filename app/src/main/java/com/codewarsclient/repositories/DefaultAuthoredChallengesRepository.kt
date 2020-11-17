package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.AuthoredChallengeDao
import com.codewarsclient.repositories.helpers.ApiResultWrapper
import com.codewarsclient.repositories.helpers.AuthoredChallengeWrapper
import com.codewarsclient.repositories.interfaces.AuthoredChallengesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAuthoredChallengesRepository @Inject constructor(
    private val dao: AuthoredChallengeDao,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(), AuthoredChallengesRepository {

    /**
     * Searches the member authored challenges
     *
     * # API communication success
     * - Stores the challenges in the local database and returns them
     *
     * # API communication failure or Network error
     * - Attempts to obtain the challenges from the local database
     */
    override suspend fun getMemberAuthoredChallenges(username: String): AuthoredChallengeWrapper {
        val challengesApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberAuthoredChallenges(username)
        }

        return when (challengesApiResponse) {
            is ApiResultWrapper.Success -> {
                val challengesApiList =
                    challengesApiResponse.value.toAuthoredChallengeEntityList(username)

                dao.insertChallengesList(challengesApiList)

                AuthoredChallengeWrapper(true, challengesApiList)
            }

            else -> AuthoredChallengeWrapper(false, dao.getChallengesFromMember(username))
        }
    }

}