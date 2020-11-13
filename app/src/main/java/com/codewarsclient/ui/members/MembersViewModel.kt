package com.codewarsclient.ui.members

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.database.entities.MemberEntity
import com.codewarsclient.repositories.MemberRepository
import kotlinx.coroutines.launch

class MembersViewModel @ViewModelInject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    val membersListAdapter: RecyclerView.Adapter<*> = MembersListAdapter()

    val selectedMember = MutableLiveData<MemberEntity>()

    private val _isToShowError = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isToShowError: LiveData<Boolean> = _isToShowError

    val listOfSearchedMembers: LiveData<List<MemberEntity>> =
        memberRepository.getLastSearchedMembers()

    fun searchMemberByName(username: String) {
        viewModelScope.launch {
            val memberFound = memberRepository.searchMemberAndSave(username)

            _isToShowError.value = !memberFound
        }
    }

}