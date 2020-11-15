package com.codewarsclient.api.models

import com.google.gson.annotations.SerializedName

data class AuthoredChallengesModel(
    @SerializedName("data")
    val challengesList: List<AuthoredChallengeDetailsModel>
)