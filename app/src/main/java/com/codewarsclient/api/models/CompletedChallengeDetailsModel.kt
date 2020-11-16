package com.codewarsclient.api.models

import com.codewarsclient.database.entities.CompletedChallengeEntity
import com.google.gson.annotations.SerializedName

data class CompletedChallengeDetailsModel(
    @SerializedName("id")
    val challengeId: String,

    @SerializedName("name")
    val challengeName: String?,

    @SerializedName("completedLanguages")
    val languagesList: List<String>,

    @SerializedName("completedAt")
    val completedAt: String
) {
    fun toCompletedChallengeEntity(username: String): CompletedChallengeEntity {
        return CompletedChallengeEntity(
            username,
            challengeId,
            challengeName ?: "",
            languagesList,
            completedAt
        )
    }
}