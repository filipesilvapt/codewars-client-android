package com.codewarsclient.ui.members

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewarsclient.models.MemberModel
import com.codewarsclient.repositories.MemberRepository
import com.codewarsclient.repositories.RepositoryResultWrapper
import kotlinx.coroutines.launch

class MembersViewModel @ViewModelInject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val _memberSearchResult = MutableLiveData<MemberModel>()
    val memberSearchResult: LiveData<MemberModel> = _memberSearchResult

    private val _isToShowError = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isToShowError: LiveData<Boolean> = _isToShowError

    fun searchMemberByName(username: String) {
        viewModelScope.launch {
            val memberSearchResponse = memberRepository.getMemberByName(username)
            when (memberSearchResponse) {
                //is RepositoryResultWrapper.NetworkError -> showNetworkError()
                is RepositoryResultWrapper.Failure -> _isToShowError.value = true
                is RepositoryResultWrapper.Success -> {
                    _isToShowError.value = false
                    _memberSearchResult.value = memberSearchResponse.value
                }
            }
        }
    }

}