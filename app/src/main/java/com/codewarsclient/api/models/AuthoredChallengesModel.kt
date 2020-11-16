package com.codewarsclient.api.models

import com.codewarsclient.database.entities.AuthoredChallengeEntity
import com.google.gson.annotations.SerializedName

data class AuthoredChallengesModel(
    @SerializedName("data")
    val challengesList: List<AuthoredChallengeDetailsModel>
) {
    fun toAuthoredChallengeEntityList(username: String): List<AuthoredChallengeEntity> {
        val entityList = ArrayList<AuthoredChallengeEntity>()

        challengesList.forEach { entityList.add(it.toAuthoredChallengeEntity(username)) }

        return entityList
    }
}