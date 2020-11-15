package com.codewarsclient.models

import com.google.gson.annotations.SerializedName

data class AuthoredChallengesModel(
    @SerializedName("data")
    val challengesList: List<AuthoredChallengeDetailsModel>
)