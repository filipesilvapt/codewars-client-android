package com.codewarsclient.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.codewarsclient.database.entities.CompletedChallengesEntity

@Dao
interface CompletedChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallengesList(member: List<CompletedChallengesEntity>)
}