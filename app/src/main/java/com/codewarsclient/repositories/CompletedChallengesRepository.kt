package com.codewarsclient.repositories

import com.codewarsclient.api.ApiService
import com.codewarsclient.database.dao.CompletedChallengeDao
import com.codewarsclient.repositories.helpers.ApiResultWrapper
import com.codewarsclient.repositories.helpers.CompletedChallengeWrapper
import com.codewarsclient.utils.DateTimeUtils
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

    /**
     * Searches a subset of the member completed challenges.
     *
     * # API communication success
     * - Requests the API a given page that should provide up to 200 challenges
     * - Inserts them into the local database which ignores any repeated items that could origin
     * from new challenges being done and shifting the API data through their pagination
     * - Case #1: The received data contains at least one item older than the provided datetime
     *   - Retrieves a subset of challenges from the local database, from a given datetime until it
     *   reaches the datetime of the last challenge received from the API (could be 1 to 200
     *   depending on the amount of repeated items received from the API)
     * - Case #2: The received data is newer than the provided datetime
     *   - The data was already inserted into the local database as it could contain new items that
     *   we didn't have and will be useful later for offline usage
     *   - Automatically requests the API for the next page in order to try and get older challenges
     *
     * # API communication failure or Network error
     * - Retrieves a subset of up to 200 challenges from the local database, from a given datetime
     * downwards
     */
    suspend fun getMemberCompletedChallenges(
        username: String,
        pageNumber: Int,
        lastShownChallengeCompletedAt: String = DateTimeUtils.getCurrentApiDateTime()
    ): CompletedChallengeWrapper {
        val challengesApiResponse = safeApiCall(dispatcher) {
            apiService.getMemberCompletedChallenges(username, pageNumber)
        }

        return when (challengesApiResponse) {
            is ApiResultWrapper.Success -> {
                // Convert to entity
                val challengesApiList =
                    challengesApiResponse.value.toCompletedChallengeEntityList(username)

                if (challengesApiList.isNotEmpty()) {
                    // Save the challenges locally (avoids possible repeated values)
                    dao.insertChallengesList(challengesApiList)

                    val lastApiChallengeCompletedAt = challengesApiList.last().completedAt

                    if (DateTimeUtils.isOneApiDateTimeOlderThanTheOther(
                            lastApiChallengeCompletedAt,
                            lastShownChallengeCompletedAt
                        )
                    ) {
                        val challengesDatabaseList = dao.getChallengesFromMemberBetweenDates(
                            username,
                            lastShownChallengeCompletedAt,
                            lastApiChallengeCompletedAt
                        )

                        CompletedChallengeWrapper(true, pageNumber, challengesDatabaseList)
                    } else {
                        getMemberCompletedChallenges(
                            username,
                            pageNumber + 1,
                            lastShownChallengeCompletedAt
                        )
                    }

                } else {
                    CompletedChallengeWrapper(true, pageNumber, emptyList())
                }

            }

            else -> CompletedChallengeWrapper(
                false,
                pageNumber,
                dao.getChallengesFromMemberAndDate(username, lastShownChallengeCompletedAt)
            )
        }
    }

}