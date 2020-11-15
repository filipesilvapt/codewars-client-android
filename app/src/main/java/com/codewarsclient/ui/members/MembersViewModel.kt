package com.codewarsclient.ui.members

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewarsclient.R
import com.codewarsclient.database.entities.MemberEntity
import com.codewarsclient.repositories.MemberRepository
import kotlinx.coroutines.launch

class MembersViewModel @ViewModelInject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    val membersListAdapter = MembersListAdapter()

    val listOfSearchedMembers: LiveData<List<MemberEntity>> =
        memberRepository.getLastSearchedMembers()

    val selectedMember = membersListAdapter.selectedMember

    val usernameToSearch = MutableLiveData<String>().apply {
        value = ""
    }

    private val _searchErrorTextResId = MutableLiveData<Int>().apply {
        value = null
    }
    val searchErrorTextResId: LiveData<Int> = _searchErrorTextResId

    /**
     * Listener for when the focus changes on the member search field
     */
    val onMemberSearchFieldFocusChangedListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            clearErrorMessage()
        }
    }

    /**
     * Validates the input field, setting error messages accordingly and gets the member from
     * the repository
     */
    fun searchMemberFromInputField() {
        // Remove all leading and trailing whitespaces
        val memberName = usernameToSearch.value.toString().trim()

        if (memberName.isEmpty()) {
            setErrorMessageEmptyField()
        } else {
            // Update the value with the normalised one
            usernameToSearch.value = memberName

            getMemberFromRepository(memberName)
        }
    }

    /**
     * Gets the member from the repository
     */
    private fun getMemberFromRepository(memberName: String) {
        viewModelScope.launch {
            val memberFound = memberRepository.searchMemberAndSave(memberName)

            if (!memberFound) {
                setErrorMessageMemberNotFound()
            }
        }
    }

    /**
     * Sets the error message for an empty field and clears the search box to remove any spaces
     */
    private fun setErrorMessageEmptyField() {
        _searchErrorTextResId.value = R.string.error_search_member_empty
        usernameToSearch.value = ""
    }

    /**
     * Sets the error message for a member not found
     */
    private fun setErrorMessageMemberNotFound() {
        _searchErrorTextResId.value = R.string.error_search_member_not_found
    }

    /**
     * Sets the error message for a member not found
     */
    private fun clearErrorMessage() {
        _searchErrorTextResId.value = null
    }

    /**
     * Sorts the members list inside the adapter according to the sorting option
     */
    fun sortMembersList(sortOption: MembersSortOption) {
        membersListAdapter.sortMembersList(sortOption)
    }

}