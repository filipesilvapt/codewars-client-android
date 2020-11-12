package com.codewarsclient.models

import com.google.gson.annotations.SerializedName

data class MemberModel(
    @SerializedName("username")
    val username: String,

    @SerializedName("ranks")
    val ranksList: RanksListModel
)