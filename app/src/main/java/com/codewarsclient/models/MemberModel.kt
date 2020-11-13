package com.codewarsclient.models

import com.codewarsclient.database.entities.MemberEntity
import com.google.gson.annotations.SerializedName

data class MemberModel(
    @SerializedName("username")
    val username: String,

    @SerializedName("ranks")
    val ranksList: RanksListModel
) {
    fun toMemberEntity(): MemberEntity {
        val highestRankedLanguage = ranksList.getRanksByLanguage().maxByOrNull { it.score }

        return MemberEntity(
            username,
            ranksList.overallRank.rank,
            ranksList.overallRank.name,
            highestRankedLanguage?.language ?: "",
            highestRankedLanguage?.score ?: 0
        ).updateTimeOfSearchWithNow()
    }
}