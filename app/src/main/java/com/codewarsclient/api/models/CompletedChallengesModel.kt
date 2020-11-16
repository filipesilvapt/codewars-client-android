package com.codewarsclient.api.models

import com.codewarsclient.database.entities.CompletedChallengeEntity
import com.google.gson.annotations.SerializedName

data class CompletedChallengesModel(
    @SerializedName("totalItems")
    val totalChallengesCompleted: Int,

    @SerializedName("data")
    val challengesList: List<CompletedChallengeDetailsModel>
) {
    fun toCompletedChallengeEntityList(username: String): List<CompletedChallengeEntity> {
        val entityList = ArrayList<CompletedChallengeEntity>()

        challengesList.forEach { entityList.add(it.toCompletedChallengeEntity(username)) }

        return entityList
    }
}