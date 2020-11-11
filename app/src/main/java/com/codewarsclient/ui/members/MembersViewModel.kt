package com.codewarsclient.ui.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MembersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is members Fragment"
    }
    val text: LiveData<String> = _text
}