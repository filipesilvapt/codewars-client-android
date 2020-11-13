package com.codewarsclient.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codewarsclient.database.entities.MemberEntity

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: MemberEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMember(member: MemberEntity)

    @Query("SELECT * FROM member ORDER BY time_of_search DESC LIMIT 5")
    fun getLastSearchedMembers(): LiveData<List<MemberEntity>>

    @Query("SELECT * FROM member WHERE username LIKE :username")
    suspend fun getMember(username: String): MemberEntity?
}