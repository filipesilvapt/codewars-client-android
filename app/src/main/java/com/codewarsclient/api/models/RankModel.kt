package com.codewarsclient.api.models

import com.google.gson.annotations.SerializedName

/**
 * Example of a model:
 * "rank": -4, "name": "4 kyu", "color": "blue", "score": 741
 *
 * A language name can also be present as the key of a Rank object.
 */
data class RankModel(
    @SerializedName("rank")
    val rank: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String,

    @SerializedName("score")
    val score: Int,

    var language: String = ""
)