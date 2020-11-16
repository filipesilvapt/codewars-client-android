package com.codewarsclient.repositories.helpers

import com.codewarsclient.database.entities.ChallengeDetailsEntity

data class ChallengeDetailsWrapper(
    val wasObtainedFromApi: Boolean,
    val challengesDetailsEntity: ChallengeDetailsEntity?
)