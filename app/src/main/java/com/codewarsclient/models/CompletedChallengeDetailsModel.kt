package com.codewarsclient.models

import com.google.gson.annotations.SerializedName

data class CompletedChallengeDetailsModel(
    @SerializedName("name")
    val challengeName: String,

    @SerializedName("completedLanguages")
    val languagesList: List<String>,

    @SerializedName("completedAt")
    val completedAt: String
)