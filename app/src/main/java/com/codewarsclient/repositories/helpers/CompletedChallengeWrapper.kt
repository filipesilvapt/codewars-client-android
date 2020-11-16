package com.codewarsclient.repositories.helpers

import com.codewarsclient.database.entities.CompletedChallengeEntity

data class CompletedChallengeWrapper(
    val wasObtainedFromApi: Boolean,
    val wasObtainedAtPage: Int,
    val challengesList: List<CompletedChallengeEntity>
)