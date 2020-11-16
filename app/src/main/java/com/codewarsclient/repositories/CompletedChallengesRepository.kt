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
                        val challengesDatabaseList = dao.getChallengesBetweenDates(
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
                dao.getChallengesFromDate(username, lastShownChallengeCompletedAt)
            )
        }
    }

}