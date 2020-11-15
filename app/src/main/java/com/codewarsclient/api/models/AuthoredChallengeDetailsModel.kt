package com.codewarsclient.api.models

import com.google.gson.annotations.SerializedName

data class AuthoredChallengeDetailsModel(
    @SerializedName("id")
    val challengeId: String,

    @SerializedName("name")
    val challengeName: String,

    @SerializedName("rankName")
    val rank: String,

    @SerializedName("tags")
    val tagsList: List<String>,

    @SerializedName("languages")
    val languagesList: List<String>,
)