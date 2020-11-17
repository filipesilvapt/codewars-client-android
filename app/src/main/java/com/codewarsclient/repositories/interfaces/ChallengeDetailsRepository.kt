package com.codewarsclient.repositories.interfaces

import com.codewarsclient.repositories.helpers.ChallengeDetailsWrapper

interface ChallengeDetailsRepository {
    suspend fun getChallengeDetails(challengeId: String): ChallengeDetailsWrapper
}