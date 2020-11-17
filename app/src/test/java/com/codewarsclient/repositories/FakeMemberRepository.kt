package com.codewarsclient.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codewarsclient.database.entities.MemberEntity
import com.codewarsclient.repositories.helpers.ApiResultWrapper
import com.codewarsclient.repositories.helpers.RepositoryResultState
import com.codewarsclient.repositories.interfaces.MemberRepository
import com.codewarsclient.utils.FileAccessUtil
import com.codewarsclient.utils.jsonToMemberModel

class FakeMemberRepository : MemberRepository {

    private val membersList = mutableListOf<MemberEntity>()

    private val observableMembersList = MutableLiveData<List<MemberEntity>>(membersList)

    override fun getLastSearchedMembers(): LiveData<List<MemberEntity>> {
        return observableMembersList
    }

    override suspend fun searchMemberAndSave(username: String): RepositoryResultState {
        val memberInfoFromFile = FileAccessUtil.readFileIntoString("member_wichu.json")

        val memberSearchApiResponse =
            ApiResultWrapper.Success(jsonToMemberModel(memberInfoFromFile))

        membersList.add(memberSearchApiResponse.value.toMemberEntity())

        observableMembersList.postValue(membersList)

        return RepositoryResultState.SUCCESS_API
    }
}