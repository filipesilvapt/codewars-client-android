package com.codewarsclient.api.models

import com.codewarsclient.database.entities.CompletedChallengesEntity
import com.google.gson.annotations.SerializedName

data class CompletedChallengesModel(
    @SerializedName("totalItems")
    val totalChallengesCompleted: Int,

    @SerializedName("data")
    val challengesList: List<CompletedChallengeDetailsModel>
) {
    fun toCompletedChallengeEntityList(username: String): List<CompletedChallengesEntity> {
        val entityList = ArrayList<CompletedChallengesEntity>()

        challengesList.forEach { entityList.add(it.toCompletedChallengeEntity(username)) }

        return entityList
    }
}