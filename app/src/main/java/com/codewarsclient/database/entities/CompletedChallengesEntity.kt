package com.codewarsclient.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "completed_challenges", primaryKeys = ["member_username", "challenge_id"])
class CompletedChallengesEntity(
    @ColumnInfo(name = "member_username")
    val memberUsername: String = "",

    @ColumnInfo(name = "challenge_id")
    val challengeId: String = "",

    @ColumnInfo(name = "challenge_name")
    val challengeName: String = "",

    @ColumnInfo(name = "languages")
    val languagesList: List<String> = emptyList(),

    @ColumnInfo(name = "completed_at")
    val completedAt: String = ""
)