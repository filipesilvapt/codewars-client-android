package com.codewarsclient.repositories.interfaces

import com.codewarsclient.repositories.helpers.CompletedChallengeWrapper
import com.codewarsclient.utils.DateTimeUtils

interface CompletedChallengesRepository {
    suspend fun getMemberCompletedChallenges(
        username: String,
        pageNumber: Int,
        lastShownChallengeCompletedAt: String = DateTimeUtils.getCurrentApiDateTime()
    ): CompletedChallengeWrapper
}