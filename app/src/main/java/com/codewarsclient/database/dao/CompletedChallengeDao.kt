package com.codewarsclient.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewarsclient.database.entities.CompletedChallengeEntity

@Dao
interface CompletedChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallengesList(member: List<CompletedChallengeEntity>)

    @Query("SELECT * FROM completed_challenges WHERE member_username LIKE :username AND datetime(completed_at) < datetime(:lastShownChallengeCompletedAt) ORDER BY datetime(completed_at) DESC LIMIT 200")
    suspend fun getChallengesFromMemberAndDate(
        username: String,
        lastShownChallengeCompletedAt: String
    ): List<CompletedChallengeEntity>

    @Query("SELECT * FROM completed_challenges WHERE member_username LIKE :username AND datetime(completed_at) < datetime(:lastShownChallengeCompletedAt) AND datetime(completed_at) >= datetime(:lastApiChallengeCompletedAt) ORDER BY datetime(completed_at) DESC LIMIT 200")
    suspend fun getChallengesFromMemberBetweenDates(
        username: String,
        lastShownChallengeCompletedAt: String,
        lastApiChallengeCompletedAt: String
    ): List<CompletedChallengeEntity>
}