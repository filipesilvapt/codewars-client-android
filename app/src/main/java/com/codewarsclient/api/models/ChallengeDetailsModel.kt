package com.codewarsclient.api.models

import com.codewarsclient.database.entities.ChallengeDetailsEntity
import com.google.gson.annotations.SerializedName

data class ChallengeDetailsModel(
    @SerializedName("id")
    val challengeId: String,

    @SerializedName("name")
    val challengeName: String?,

    @SerializedName("rank")
    val rank: RankModel?,

    @SerializedName("tags")
    val tagsList: List<String>,

    @SerializedName("languages")
    val languagesList: List<String>,

    @SerializedName("description")
    val description: String
) {
    fun toChallengeDetailsEntity(): ChallengeDetailsEntity {
        return ChallengeDetailsEntity(
            challengeId,
            challengeName ?: "",
            rank?.name ?: "",
            tagsList,
            languagesList,
            description
        )
    }
}