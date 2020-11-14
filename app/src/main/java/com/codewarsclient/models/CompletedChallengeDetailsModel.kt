package com.codewarsclient.models

import com.google.gson.annotations.SerializedName

data class CompletedChallengeDetailsModel(
    @SerializedName("CompletedChallengesListModel")
    val challengeName: Int,

    @SerializedName("completedLanguages")
    val languagesList: List<String>,

    @SerializedName("completedAt")
    val completedAt: String
)