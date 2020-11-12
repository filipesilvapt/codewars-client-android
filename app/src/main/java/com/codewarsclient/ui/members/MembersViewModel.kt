package com.codewarsclient.ui.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codewarsclient.models.MemberModel
import com.codewarsclient.repositories.MemberRepository
import kotlinx.coroutines.Dispatchers

class MembersViewModel : ViewModel() {

    private val _membersList = MutableLiveData<List<MemberModel>>().apply {
        value = emptyList()
    }
    val membersList: LiveData<List<MemberModel>> = _membersList

    fun searchMemberByName(username: String) = liveData(Dispatchers.IO) {
        val foundMember = MemberRepository().getMemberByName(username)

        emit(foundMember)
    }

}