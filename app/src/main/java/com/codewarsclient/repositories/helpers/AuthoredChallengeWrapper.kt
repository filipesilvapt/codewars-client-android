package com.codewarsclient.repositories.helpers

import com.codewarsclient.database.entities.AuthoredChallengeEntity

data class AuthoredChallengeWrapper(
    val wasObtainedFromApi: Boolean,
    val challengesList: List<AuthoredChallengeEntity>
)