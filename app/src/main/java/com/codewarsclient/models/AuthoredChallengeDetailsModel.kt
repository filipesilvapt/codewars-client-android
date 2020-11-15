package com.codewarsclient.models

import com.google.gson.annotations.SerializedName

data class AuthoredChallengeDetailsModel(
    @SerializedName("name")
    val challengeName: String,

    @SerializedName("rankName")
    val rank: String,

    @SerializedName("tags")
    val tagsList: List<String>,

    @SerializedName("languages")
    val languagesList: List<String>,
)