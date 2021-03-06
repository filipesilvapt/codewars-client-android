package com.codewarsclient.repositories.interfaces

import androidx.lifecycle.LiveData
import com.codewarsclient.database.entities.MemberEntity
import com.codewarsclient.repositories.helpers.RepositoryResultState

interface MemberRepository {
    fun getLastSearchedMembers(): LiveData<List<MemberEntity>>
    suspend fun searchMemberAndSave(username: String): RepositoryResultState
    suspend fun insertMemberInDatabase(memberEntity: MemberEntity)
    suspend fun updateMemberInDatabase(memberEntity: MemberEntity)
    suspend fun getMemberFromDatabase(username: String): MemberEntity?
}