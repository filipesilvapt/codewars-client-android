package com.codewarsclient.ui.members

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewarsclient.R
import com.codewarsclient.database.entities.MemberEntity
import com.codewarsclient.repositories.MemberRepository
import com.codewarsclient.repositories.helpers.RepositoryResultState
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

    private val _searchHelperTextResId = MutableLiveData<Int>().apply {
        value = null
    }
    val searchHelperTextResId: LiveData<Int> = _searchHelperTextResId

    /**
     * Listener for when the focus changes on the member search field
     */
    val onMemberSearchFieldFocusChangedListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            clearSearchFieldMessages()
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
            when (val result = memberRepository.searchMemberAndSave(memberName)) {
                RepositoryResultState.SUCCESS_DB_AFTER_API -> setHelperMessage(R.string.error_search_member_found_db_after_api)
                RepositoryResultState.SUCCESS_DB_OFFLINE -> setHelperMessage(R.string.message_search_member_found_offline)
                RepositoryResultState.NOT_FOUND_API -> setErrorMessage(R.string.error_search_member_not_found)
                RepositoryResultState.NOT_FOUND_DB_OFFLINE -> setHelperMessage(R.string.message_search_member_not_found_offline)
                RepositoryResultState.ERROR_API -> setErrorMessage(R.string.error_search_member_not_found_api_error)
                else -> Log.v(TAG, "Repository state $result not treated")
            }
        }
    }

    /**
     * Sets the error message for an empty field and clears the search box to remove any spaces
     */
    private fun setErrorMessageEmptyField() {
        setErrorMessage(R.string.error_search_member_empty)
        usernameToSearch.value = ""
    }

    /**
     * Sets the error message with a given resource id
     */
    private fun setErrorMessage(resourceTextId: Int) {
        _searchErrorTextResId.value = resourceTextId
    }

    /**
     * Sets the helper message with a given resource id
     */
    private fun setHelperMessage(resourceTextId: Int) {
        _searchHelperTextResId.value = resourceTextId
    }

    /**
     * Clears the helper and error messages from the search field
     */
    private fun clearSearchFieldMessages() {
        _searchErrorTextResId.value = null
        _searchHelperTextResId.value = null
    }

    /**
     * Sorts the members list inside the adapter according to the sorting option
     */
    fun sortMembersList(sortOption: MembersSortOption) {
        membersListAdapter.sortMembersList(sortOption)
    }

    companion object {
        private val TAG: String = MembersViewModel::class.java.simpleName
    }

}