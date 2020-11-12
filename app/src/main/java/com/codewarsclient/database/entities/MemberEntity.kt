package com.codewarsclient.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member")
class MemberEntity(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val username: String = "",

    @ColumnInfo(name = "rank")
    val rank: Int = 0,

    @ColumnInfo(name = "rank_name")
    val rankName: String = "",

    @ColumnInfo(name = "best_language")
    val bestLanguage: String = "",

    @ColumnInfo(name = "best_language_points")
    val bestLanguagePoints: Int = 0,

    @ColumnInfo(name = "time_of_search")
    val timeOfSearch: Long = 0
)