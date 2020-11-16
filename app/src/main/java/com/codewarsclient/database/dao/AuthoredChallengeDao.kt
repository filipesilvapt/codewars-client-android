package com.codewarsclient.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewarsclient.database.entities.AuthoredChallengeEntity

@Dao
interface AuthoredChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallengesList(member: List<AuthoredChallengeEntity>)

    @Query("SELECT * FROM authored_challenges WHERE member_username LIKE :username")
    suspend fun getChallengesFromMember(username: String): List<AuthoredChallengeEntity>
}