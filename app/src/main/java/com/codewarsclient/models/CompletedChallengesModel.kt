package com.codewarsclient.models

import com.google.gson.annotations.SerializedName

data class CompletedChallengesModel(
    @SerializedName("totalItems")
    val totalChallengesCompleted: Int,

    @SerializedName("data")
    val challengesList: List<CompletedChallengeDetailsModel>
)