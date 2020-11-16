package com.codewarsclient.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewarsclient.database.entities.ChallengeDetailsEntity

@Dao
interface ChallengeDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallengeDetails(member: ChallengeDetailsEntity)

    @Query("SELECT * FROM challenges_details WHERE challenge_id LIKE :challengeId")
    suspend fun getChallengeDetails(challengeId: String): ChallengeDetailsEntity
}