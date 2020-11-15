package com.codewarsclient.api.models

import com.codewarsclient.database.entities.CompletedChallengesEntity
import com.google.gson.annotations.SerializedName

data class CompletedChallengeDetailsModel(
    @SerializedName("id")
    val challengeId: String,

    @SerializedName("name")
    val challengeName: String,

    @SerializedName("completedLanguages")
    val languagesList: List<String>,

    @SerializedName("completedAt")
    val completedAt: String
) {
    fun toCompletedChallengeEntity(username: String): CompletedChallengesEntity {
        return CompletedChallengesEntity(
            username,
            challengeId,
            challengeName,
            languagesList,
            completedAt
        )
    }
}