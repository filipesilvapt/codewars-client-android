package com.codewarsclient.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges_details")
class ChallengeDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = "challenge_id")
    val challengeId: String = "",

    @ColumnInfo(name = "challenge_name")
    val challengeName: String = "",

    @ColumnInfo(name = "rank_name")
    val rankName: String = "",

    @ColumnInfo(name = "tags")
    val tagsList: List<String> = emptyList(),

    @ColumnInfo(name = "languages")
    val languagesList: List<String> = emptyList(),

    @ColumnInfo(name = "description")
    val description: String = ""
)