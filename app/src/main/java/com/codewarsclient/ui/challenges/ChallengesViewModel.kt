package com.codewarsclient.ui.challenges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ChallengesViewModel : ViewModel() {

    private val _textUsername = MutableLiveData<String>().apply {
        value = ""
    }
    val textUsername: LiveData<String> = _textUsername

    fun setMemberUsername(username: String) {
        _textUsername.value = username
    }

    fun getMemberUsername(): String {
        return _textUsername.value ?: ""
    }
}