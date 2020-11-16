package com.codewarsclient.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "authored_challenges", primaryKeys = ["member_username", "challenge_id"])
class AuthoredChallengeEntity(
    @ColumnInfo(name = "member_username")
    val memberUsername: String = "",

    @ColumnInfo(name = "challenge_id")
    val challengeId: String = "",

    @ColumnInfo(name = "challenge_name")
    val challengeName: String = "",

    @ColumnInfo(name = "tags")
    val tagsList: List<String> = emptyList(),

    @ColumnInfo(name = "languages")
    val languagesList: List<String> = emptyList()
)