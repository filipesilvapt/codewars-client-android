package com.codewarsclient.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.codewarsclient.database.entities.AuthoredChallengeEntity

@Dao
interface AuthoredChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallengesList(member: List<AuthoredChallengeEntity>)
}