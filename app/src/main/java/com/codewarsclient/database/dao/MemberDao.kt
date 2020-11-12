package com.codewarsclient.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewarsclient.database.entities.MemberEntity

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(member: MemberEntity)

    @Query("SELECT * FROM member ORDER BY time_of_search DESC LIMIT 5")
    fun getLastSearchedMembers(): LiveData<List<MemberEntity>>
}