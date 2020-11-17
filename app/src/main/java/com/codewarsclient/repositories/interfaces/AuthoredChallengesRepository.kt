package com.codewarsclient.repositories.interfaces

import com.codewarsclient.repositories.helpers.AuthoredChallengeWrapper

interface AuthoredChallengesRepository {
    suspend fun getMemberAuthoredChallenges(username: String): AuthoredChallengeWrapper
}