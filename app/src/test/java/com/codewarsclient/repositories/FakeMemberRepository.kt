package com.codewarsclient.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codewarsclient.api.ApiService
import com.codewarsclient.database.entities.MemberEntity
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FakeMemberRepository(
    apiService: ApiService,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DefaultMemberRepository(mockk(), apiService, dispatcher) {

    private val membersList = mutableListOf<MemberEntity>()

    private val observableMembersList = MutableLiveData<List<MemberEntity>>(membersList)

    override fun getLastSearchedMembers(): LiveData<List<MemberEntity>> {
        return observableMembersList
    }

    override suspend fun insertMemberInDatabase(memberEntity: MemberEntity) {
        upsertMember(memberEntity)
    }

    override suspend fun updateMemberInDatabase(memberEntity: MemberEntity) {
        upsertMember(memberEntity)
    }

    override suspend fun getMemberFromDatabase(username: String): MemberEntity? {
        return membersList.find { member -> username == member.username }
    }

    private fun upsertMember(memberEntity: MemberEntity) {
        // Always remove the element from the list to mimic the on conflict, replace, database strategy
        membersList.removeIf { member -> memberEntity.username == member.username }
        // Always add at the top to mimic the database query which sorts by time of search
        membersList.add(0, memberEntity)
        // Update the live data
        observableMembersList.postValue(membersList)
    }
}