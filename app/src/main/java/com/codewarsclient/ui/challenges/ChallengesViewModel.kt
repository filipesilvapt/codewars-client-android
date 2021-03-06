package com.codewarsclient.ui.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codewarsclient.R

abstract class ChallengesViewModel : ViewModel() {

    private val _textUsername = MutableLiveData<String>().apply {
        value = ""
    }
    val textUsername: LiveData<String> = _textUsername

    private val _textStatusMessageResId = MutableLiveData<Int>().apply {
        value = null
    }
    val textStatusMessageResId: LiveData<Int> = _textStatusMessageResId

    var endOfListReached: Boolean = false
        private set

    fun setMemberUsername(username: String) {
        _textUsername.value = username
    }

    fun getMemberUsername(): String {
        return _textUsername.value ?: ""
    }

    abstract fun getListAdapter(): ChallengesListAdapter<*>

    fun setNoMoreResultsStatus() {
        endOfListReached = true

        _textStatusMessageResId.value = R.string.status_no_more_results
    }

    fun setOfflineLoadStatus() {
        _textStatusMessageResId.value = R.string.status_challenges_offline_load
    }

    fun clearStatusMessage() {
        _textStatusMessageResId.value = null
    }

}