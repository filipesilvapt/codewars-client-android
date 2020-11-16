package com.codewarsclient.ui.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
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

    abstract fun getListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

    fun setEndOfListReachedStatus() {
        endOfListReached = true

        _textStatusMessageResId.value = R.string.status_end_of_list
    }

    fun setOfflineLoadStatus() {
        _textStatusMessageResId.value = R.string.status_offline_load
    }

    fun clearStatusMessage() {
        _textStatusMessageResId.value = null
    }
}